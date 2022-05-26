<template>
  <div id="container">
    <b-sidebar
      id="detail-info-sidebar"
      v-model="isSidebarOpen"
      title=""
      right
      width="428px"
      shadow="lg"
      bg-variant="white"
    >
      <info-detail :selectedInfo="selectedInfo"></info-detail>
    </b-sidebar>
    <house-search-bar
      class="rounded shadow"
      id="house-search-bar"
    ></house-search-bar>
    <house-list
      id="house-list"
      class="position-absolute border border-light rounded shadow"
      v-show="this.houses && this.houses.length != 0"
    />
    <map-input
      @search-query="searchQuery"
      @result-click="moveMapToPosition"
      @result-visible="changeResultVisible"
      @modify-filter="modifyFilter"
      :resultSearch="resultSearch"
      :resultVisible="resultVisible"
    ></map-input>
    <div id="map" class="map"></div>
  </div>
</template>

<script>
import MapInput from "@/components/map/MapInput.vue";
import InfoDetail from "@/components/map/InfoDetail.vue";
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import { mapGetters, mapActions, mapState } from "vuex";

// const MAP_APP_KEY = 'fdf614748efadd63bf7ce73b5ddad4f1';
// const GMAP_APP_KEY = process.env.VUE_APP_GMAP_APP_KEY;

export default {
  components: { MapInput, InfoDetail, HouseSearchBar, HouseList },
  data() {
    return {
      map: null,
      markers: [],
      clusterer: null,
      ps: null,
      isSidebarOpen: false,
      resultVisible: false,
      selectedInfo: {},
      resultSearch: {},
      rangeDeal: 20,
      rangeInfra: 0,
      circles: [],
    };
  },

  created() {
    this.$store.commit("SET_HOUSE_LIST", []);
  },

  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addMapScript();
  },

  computed: {
    ...mapState(["avgs", "houses"]),
    ...mapGetters(["getHouseInfos", "getCenterLatLng"]),
  },

  watch: {
    avgs: function (val) {
      this.displayCircles(val);
    },
    getHouseInfos: function (val) {
      // console.log("61.updateMap", val);
      this.updateMap(val);
    },
    getCenterLatLng: function (val) {
      console.log("==========");
      console.log(val);
      this.setCenter(val);
    },
  },

  methods: {
    ...mapActions(["HOUSEINFO", "MOVEMAP", "getAvg"]),

    addMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" +
        "fdf614748efadd63bf7ce73b5ddad4f1" +
        "&libraries=services,clusterer";
      document.head.appendChild(script);
    },
    displayCircles(places) {
      console.log("시세 표시");
      this.clearMarkers(null);
      // 기존에 시세 마커 없애기
      if (this.circles) {
        var circles = this.circles;
        for (let i = 0; i < circles.length; i++) {
          circles[i].setMap(null);
        }
        this.circles = [];
      }
      // // 기존에 있던 마커 제거
      // if (this.markers) {
      //   var markers = this.markers;
      //   for (let i = 0; i < markers.length; i++) {
      //     markers[i].setMap(null);
      //   }
      //   this.markers = [];
      // }
      for (let i = 0; i < places.length; i++) {
        // 시세 정보 넣기
        var content = `<div class ="label" style="background-color : #dba617;color:white;font-weight:bold;
        border-radius: 15px;padding: 10px;box-shadow: 1px 1px 5px gray;"><div class="left">
        ${places[i].name}</div><div class="center">${places[
          i
        ].avg.toLocaleString("ko-KR")} 만원</div></div>`;
        // var content = `${places[i].name} +
        // " "${places[i].avg.toLocaleString("ko-KR")}`;
        // 커스텀 오버레이가 표시될 위치입니다
        var position = new kakao.maps.LatLng(places[i].lat, places[i].lng);
        // 커스텀 오버레이를 생성합니다
        var customOverlay = new kakao.maps.CustomOverlay({
          position: position,
          content: content,
        });
        this.circles.push(customOverlay);
        // 커스텀 오버레이를 지도에 표시합니다
        customOverlay.setMap(this.map);
      }
    },

    initMap() {
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.5665734, 126.978179), //지도의 중심좌표.
        level: 4, //지도의 레벨(확대, 축소 정도)
        maxLevel: 12,
      };

      this.map = new kakao.maps.Map(container, options); //지도 생성 및 객체

      var mapTypeControl = new kakao.maps.MapTypeControl();
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      // 지도가 이동, 확대, 축소로 인해 지도영역이 변경되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(this.map, "tilesloaded", this.moveMap);
      kakao.maps.event.addListener(this.map, "click", this.clickMap);
      kakao.maps.event.addListener(this.map, "zoom_changed", this.changeAvg);

      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 2, // 클러스터 할 최소 지도 레벨
      });

      this.ps = new kakao.maps.services.Places();
      // 라우터로 값이 넘어왔으면 맵 이동
      console.log("this.$route.params", this.$route.params);
      if (this.$route.params) {
        console.log("this.$route.params가 있음", this.$route.params);
        this.moveMapToPosition(this.$route.params);
      }
    },

    changeAvg() {
      // 지도의 현재 레벨을 얻어옵니다
      var level = this.map.getLevel();
      console.log("현재 지도 레벨은 " + level + " 입니다");
      // 동이 선택되지 않아서 아무 아파트 리스트가 매핑 되지 않을 때만 동작

      // if (!(this.houses && this.houses.length != 0)) {
      if (level >= 10) {
        // 시도 시세 출력
        this.clusterer.clear();
        this.getAvg("sido");
      } else if (level >= 5) {
        this.clusterer.clear();
        // 시도 구군 출력
        this.getAvg("gugun");
      } else if (level > 7) {
        // 시도 동 출력
        this.getAvg("dong");
      } else {
        this.$store.commit("SET_AVG", []);
      }
      // }
    },

    searchQuery(query) {
      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
      if (query) {
        this.ps.keywordSearch(query, this.placesSearchCB);
      }
    },

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // mapinput에 보내기
        this.resultSearch = {
          data,
          pagination,
        };
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        this.resultSearch = {};
        return;
      } else if (status === kakao.maps.services.Status.ERROR) {
        alert("검색 결과 중 오류가 발생했습니다.");
        return;
      }
    },

    moveMap() {
      // 지도 영역정보를 얻어옵니다
      var level = this.map.getLevel();

      if (level <= 5) {
        var bounds = this.map.getBounds();
        // 영역정보의 남서쪽 정보를 얻어옵니다
        var swLatlng = bounds.getSouthWest();
        // 영역정보의 북동쪽 정보를 얻어옵니다
        var neLatlng = bounds.getNorthEast();

        console.log("sw = " + swLatlng);
        console.log("ne = " + neLatlng);

        this.MOVEMAP({
          swlat: swLatlng.Ma,
          swlng: swLatlng.La,
          nelat: neLatlng.Ma,
          nelng: neLatlng.La,
          deal: this.rangeDeal,
          infra: this.rangeInfra,
        });
      }
    },
    setCenter(latLng) {
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(latLng[0], latLng[1]);
      // 지도 중심을 이동 시킵니다
      console.log("============");
      console.log("map level: " + this.$store.state.mapLevel);

      this.map.setLevel(this.$store.state.mapLevel);
      this.map.setCenter(moveLatLon);
    },

    moveMapToPosition(position) {
      this.resultVisible = false;
      this.map.setLevel(4);
      this.map.panTo(new kakao.maps.LatLng(position.lat, position.lng));
      this.moveMap();
    },

    updateMap(houseinfos) {
      this.clearMarkers(null);
      this.clusterer.clear();
      var level = this.map.getLevel();
      console.log("현재 지도의 레벨 : " + this.map.getLevel());

      if (level >= 5) {
        this.clearMarkers(null);
        return;
      }
      if (this.circles) {
        var circles = this.circles;
        for (let i = 0; i < circles.length; i++) {
          circles[i].setMap(null);
        }
        this.circles = [];
      }

      if (level >= 3) {
        console.log("표시한다", houseinfos.length);
        for (let i = 0; i < houseinfos.length; i++) {
          var marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(
              houseinfos[i].lat,
              houseinfos[i].lng,
            ),
            clickable: true,
          });
          var open = this.openSidebar;
          var select = this.selectInfo;
          kakao.maps.event.addListener(marker, "click", function () {
            select(houseinfos[i]);
            open();
          });

          this.markers.push(marker);
        }

        this.clusterer.addMarkers(this.markers);

        return;
      }

      for (let i = 0; i < houseinfos.length; i++) {
        const position = new kakao.maps.LatLng(
          houseinfos[i].lat,
          houseinfos[i].lng,
        );
        this.addMarker(this.map, position, houseinfos[i]);
      }
    },

    // 마커를 생성하고 지도위에 표시하는 함수입니다
    addMarker(map, position, data) {
      // var MARKER_SRC = "../assets/location64.png";
      var MARKER_SRC =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png";
      var imageSize = new kakao.maps.Size(42, 42);
      var imageOption = { offset: new kakao.maps.Point(27, 69) };

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(
        MARKER_SRC,
        imageSize,
        imageOption,
      );

      // console.log(position)
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: position,
        image: markerImage,
        clickable: true, // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(this.map);

      // 생성된 마커를 배열에 추가합니다
      this.markers.push(marker);
      // console.log("addMarker-markers", this.markers);

      var open = this.openSidebar;
      var select = this.selectInfo;
      kakao.maps.event.addListener(marker, "click", function () {
        select(data);
        open();
      });
    },

    // 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
    setMarkers(map) {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(map);
      }
    },

    clearMarkers() {
      this.setMarkers(null);
      this.markers = [];
    },

    openSidebar() {
      this.isSidebarOpen = true;
    },

    selectInfo(data) {
      this.selectedInfo = data;
    },

    clickMap() {
      this.isSidebarOpen = false;
      this.resultVisible = false;
    },

    changeResultVisible(bool) {
      this.resultVisible = bool;
    },

    modifyFilter(filter) {
      this.rangeDeal = filter.deal;
      this.rangeInfra = filter.infra;
      this.moveMap();
    },
  },
  destroyed() {
    console.log("해제~~");
    this.$store.commit("HOUSEINFO", []);
    this.$store.commit("SET_HOUSE_LIST", []);
    // this.SEARCHBYLATLNG([]);
  },
};
</script>

<style scoped>
#container {
  margin-top: 70px;
}
.map {
  width: 100%;
  min-height: 100vh;
}
#house-search-bar {
  position: absolute;
  background: white;
  left: 50%;
  top: 100px;
  width: 500px;
  border-radius: 10px;
  z-index: 3;
  transform: translate(-50%, 0);
}
#house-list {
  position: absolute;
  background: white;
  left: 30px;
  top: 250px;
  width: 300px;
  z-index: 3;
  /* transform: translate(0, -50%); */
}
</style>
