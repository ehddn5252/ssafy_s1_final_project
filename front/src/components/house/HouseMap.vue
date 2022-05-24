<template>
  <div class="map_wrap">
    <v-btn @click="clickAvg">시세보기</v-btn>
    <div
      id="map"
      style="width: 500px; height: 500px; position: relative; overflow: hidden"
    ></div>
    <div class="hAddr">
      <span class="title">지도중심기준 아파트 정보</span>
      <span id="centerAddr"></span>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { mapActions } from "vuex";
export default {
  name: "HouseStoreMap",
  data() {
    return {
      markers: [],
      infowindow: null,
      circles: [],
    };
  },
  watch: {
    houseLatLng: function (val) {
      console.log("==========");
      console.log("House val");
      console.log(val);
      this.displayMarker(val);
    },
    avgs: function () {
      this.displayCircles(this.avgs);
    },

    getCenterLatLng: function (val) {
      console.log("==========");
      console.log(val);
      this.setCenter(val);
    },
  },
  computed: {
    ...mapState(["houses", "avgs", "centerMapList"]),
    houseLatLng() {
      return this.$store.getters.houseLatLng;
    },
    getCenterLatLng() {
      return this.$store.getters.getCenterLatLng;
    },
  },
  methods: {
    ...mapState(["getLevel"]),
    ...mapActions(["getSido", "getAvg"]),
    clickAvg() {
      console.log("시세보기");
      this.$store.commit("SET_HOUSE_LIST", []);
      this.changeAvg();
    },
    // sidoList() {
    //   this.getSido();
    // },
    // gugunList() {
    //   console.log(this.sidoCode);
    //   this.CLEAR_GUGUN_LIST();
    //   this.gugunCode = null;
    //   if (this.sidoCode) this.getGugun(this.sidoCode);
    // }
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.526744, 127.19479),
        level: 8,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
      this.changeAvg();
      // 지도가 확대 또는 축소되면 특정 레벨마다 시세 출력 변경set_avg
      kakao.maps.event.addListener(this.map, "zoom_changed", this.changeAvg);
    },
    changeAvg() {
      // 지도의 현재 레벨을 얻어옵니다
      var level = this.map.getLevel();
      console.log("현재 지도 레벨은 " + level + " 입니다");
      // 동이 선택되지 않아서 아무 아파트 리스트가 매핑 되지 않을 때만 동작
      if (!(this.houses && this.houses.length != 0)) {
        if (level >= 10) {
          // 시도 시세 출력
          this.getAvg("sido");
        } else if (level >= 8) {
          // 시도 구군 출력
          this.getAvg("gugun");
        } else if (level >= 2) {
          // 시도 동 출력
          this.getAvg("dong");
        } else {
          this.$store.commit("SET_AVG", []);
        }
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
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      // 기존에 있던 원 없앰
      var circles = this.circles;
      for (var i = 0; i < circles.length; i++) {
        circles[i].setMap(null);
      }
      // 기존에 있던 마커 제거
      if (this.markers) {
        var markers = this.markers;
        for (let i = 0; i < markers.length; i++) {
          markers[i].setMap(null);
        }
        this.markers = [];
      }
      // console.log("markerPositions");
      // console.log(markerPositions);
      const positions = markerPositions.map((position) => {
        return new kakao.maps.LatLng(...position);
      });
      console.log(positions);
      if (positions.length > 0) {
        this.markers = positions.map((position) => {
          return new kakao.maps.Marker({ map: this.map, position });
        });
        var bounds = new kakao.maps.LatLngBounds();
        for (let i = 0; i < positions.length; i++) {
          bounds.extend(positions[i]);
        }
        this.map.setBounds(bounds);
      }
    },
    displayCircles(places) {
      // 기존에 시세 마커 없애기
      if (this.circles) {
        var circles = this.circles;
        for (let i = 0; i < circles.length; i++) {
          circles[i].setMap(null);
        }
        this.circles = [];
      }
      // 기존에 있던 마커 제거
      if (this.markers) {
        var markers = this.markers;
        for (let i = 0; i < markers.length; i++) {
          markers[i].setMap(null);
        }
        this.markers = [];
      }
      for (let i = 0; i < places.length; i++) {
        // 시세 정보 넣기
        var content = `<div class ="label" style="background-color : lightpink;
        border-radius: 10px;padding: 5px"><div class="left">+"                    "
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
    //
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      console.log("initmap is woking");
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
    }
  },
  //   const script = document.createElement("script");
  //   // script.type = "text/javascript";
  //   script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.VUE_APP_KAKAO_API_KEY}&libraries=services&autoload=false`;
  //   document.head.appendChild(script);
  //   const positions = this.$store.state.aroundStore;
  //   var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  //     mapOption = {
  //       center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
  //       level: 5, // 지도의 확대 레벨
  //     };
  //   var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
  //   // 마커 이미지의 이미지 주소입니다
  //   var imageSrc =
  //     "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
  //   for (var i = 0; i < positions.length; i++) {
  //     // 마커 이미지의 이미지 크기 입니다
  //     var imageSize = new kakao.maps.Size(24, 35);
  //     // 마커 이미지를 생성합니다
  //     var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
  //     // 마커를 생성합니다
  //     var marker = new kakao.maps.Marker({
  //       map: map, // 마커를 표시할 지도
  //       position: new kakao.maps.LatLng(positions[i].lat, positions[i].lng), //positions[i].latlng, // 마커를 표시할 위치
  //       title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
  //       image: markerImage, // 마커 이미지
  //     });
  //     console.log(marker);
  //   }
  //   map.setCenter(positions[0].latlng);
  // },
};
</script>

<style></style>
