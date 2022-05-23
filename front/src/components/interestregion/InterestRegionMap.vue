<template>
  <div class="map_wrap" style="position: fixed">
    <div
      id="map"
      style="width: 500px; height: 500px; position: relative; overflow: hidden"
    ></div>
    <div class="hAddr">
      <span class="title">지도중심기준 행정동 주소정보</span>
      <span id="centerAddr"></span>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "InterestRegionMap",
  data() {
    return {
      markers: [],
      infowindow: null,
    };
  },
  watch: {
    userInterestHouseLatLon: function (val) {
      this.displayMarker(val);
    },
    userInterestAroundStoreLatLon: function (val) {
      this.displayMarker(val);
    },
  },
  computed: {
    userInterestHouseLatLon() {
      return this.$store.getters.houseLatLng;
    },
    userInterestAroundStoreLatLon() {
      return this.$store.getters.aroundStoreLatLon;
    },
  },

  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    // this.CLEAR_SIDO_LIST();
  },
  methods: {
    ...mapActions(["getSido"]),
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
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `${size}px`;
      container.style.height = `${size}px`;
      this.map.relayout();
    },
    displayMarker(markerPositions) {
      console.log("markerPositions");
      console.log(markerPositions);

      var markers = this.markers;
      for (let i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
      }
      this.markers = [];

      const positions = markerPositions.map((position) => {
        return new kakao.maps.LatLng(...position);
      });
      console.log(positions);
      if (positions.length > 0) {
        this.markers = positions.map((position) => {
          return new kakao.maps.Marker({ map: this.map, position });
        });
        console.log("positions");
        for (let i = 0; i < positions.length; ++i) {
          console.log(positions[i]);
        }
        var bounds = new kakao.maps.LatLngBounds();
        for (let i = 0; i < positions.length; i++) {
          bounds.extend(positions[i]);
        }
        //=========

        // const bounds = positions.reduce((bounds, latlng) => {
        //   console.log("bounds");
        //   console.log(bounds);
        //   console.log(latlng);

        //   bounds.extend(latlng);
        // }, new kakao.maps.LatLngBounds());

        this.map.setBounds(bounds);
      }
    },
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
};
</script>

<style></style>
