import tmap from "./tmap.js";

async function checkCondition(url, params, condition) {
  var result = await tmap
    .post(url, params)
    .then((response) => {
      let time = response.data["features"][0]["properties"]["totalTime"] / 60;
      let distance =
        response.data["features"][0]["properties"]["totalDistance"];
      let text = `${Math.round(time)}분 (${
        condition.transportation
      }), ${distance}m]<br>`;
      if (time > condition.time) {
        return Promise.reject(false);
      } else {
        return text;
      }
    })
    .catch((error) => {
      return Promise.reject(error);
    });
  console.log("checkCondition 완료", result);
  return result;
}

async function findNearest(params, condition, apart) {
  console.log("0. 비동기함수 실행");
  var result2 = await tmap
    .get("/pois/search/around", { params: params })
    .then((response) => {
      // 가장 가까운 곳이 해당 아파트에서 몇 분 거리인지 계산
      var nearLng = response.data["searchPoiInfo"]["pois"]["poi"][0].frontLon;
      var nearLat = response.data["searchPoiInfo"]["pois"]["poi"][0].frontLat;
      apart.aroundStr += `<b>${response.data["searchPoiInfo"]["pois"]["poi"][0].name}</b> <br>[`;

      // 선택한 교통수단이 도보일때와 차량일 때 다르게 url 설정
      var url;
      if (condition.transportation == "도보") url = "routes/pedestrian";
      else url = "routes";

      var params = {
        version: 1,
        startX: nearLng,
        startY: nearLat,
        endX: apart.lng,
        endY: apart.lat,
        startName: "home",
        endName: "station",
      };

      // console.log("params", params);
      checkCondition(url, params, condition)
        .then((result) => {
          apart.aroundStr += result;
          console.log("0.5. checkCondition 결과 전달 ", apart);
          return apart;
        })
        .catch((error) => {
          console.log(error);
          return Promise.reject(false);
        });
    });
  console.log("1. 비동기함수 완료", result2);
  return result2;
}

async function findApt(data, conditions) {
  var result = [];
  for await (const apart of data) {
    var isSatisfied = true;

    apart.aroundStr = "";
    // 각 조건들에 부합하는지 확인
    // console.log(this.state.aptConditions);
    for await (let condition of conditions) {
      // console.log("조건 확인 들어옴");
      if (!isSatisfied) break;
      console.log("condition", condition);

      var params = {
        version: 1,
        centerLat: apart.lat,
        centerLon: apart.lng,
        categories: condition.category,
        sort: "distance",
      };

      // 가장 가까운 곳이 어딘지 검색
      var result3 = await findNearest(params, condition, apart);
      result3
        .then(() => {
          console.log("1. 비동기함수 완료");
        })
        .catch((err) => {
          console.log(err);
          isSatisfied = false;
        });
    }
    if (isSatisfied) {
      console.log("2. result에 넣음");
      result.push(apart);
    }
  }
  return result;
}

// function logout(success, fail)

export { checkCondition, findNearest, findApt };
