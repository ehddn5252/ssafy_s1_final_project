<template>
  <div class="p-3">
    <b-row class="mb-1 text-center">
      <b-col class="area-select">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col class="area-select">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col class="area-select">
        <b-form-select
          v-model="dongCode"
          :options="dongs"
          @change="searchApt"
        ></b-form-select>
      </b-col>
    </b-row>
    <b-row>
      <b-input-group>
        <b-form-input
          v-model="aptName"
          @keydown.enter="searchApt"
          placeholder="검색할 아파트명을 입력해주세요"
        ></b-form-input>
        <b-input-group-append>
          <b-button variant="info" @click="searchApt">아파트 검색</b-button>
        </b-input-group-append>
        <interest-region-register
          :sidoCode="sidoCode"
          :sigugunCode="gugunCode"
          :dongCode="dongCode"
          :areaName="areaName"
        ></interest-region-register>
      </b-input-group>
    </b-row>
    <!-- <b-row>
      <house-search-condition></house-search-condition>
    </b-row> -->
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations, mapGetters } from "vuex";
// import HouseSearchCondition from "@/components/house/HouseSearchCondition.vue";
import InterestRegionRegister from "@/components/interestregion/InterestRegionRegister.vue";

export default {
  name: "HouseSearchBar",
  components: {
    // HouseSearchCondition,
    InterestRegionRegister,
  },
  data() {
    return {
      aptName: "",
      // key: "userid",
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      conditions: [],
      areaName: null,
    };
  },
  computed: {
    ...mapState(["sidos", "guguns", "dongs", "houses", "page"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
    sidoValue() {
      return this.sidos.find((option) => option.value == this.sidoCode);
    },
    sigugunValue() {
      return this.guguns.find((option) => option.value == this.gugunCode);
    },
    dongValue() {
      return this.dongs.find((option) => option.value == this.dongCode);
    },
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions([
      "getSido",
      "getGugun",
      "getHouseList",
      "getDong",
      "getHouse",
      "getSidoCenter",
      "getGugunCenter",
    ]),

    ...mapMutations([
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_PAGE",
      "SET_NAVIGATOR",
      "SET_HOUSE_LIST",
      "SET_DONG",
    ]),
    ...mapGetters(["getCenterLatLng"]),

    sidoList() {
      this.getSido();
    },
    gugunList() {
      let params = { sido: this.sidoCode };
      if (this.sidoCode) this.getSidoCenter(params);

      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      let params = { gugun: this.gugunCode };
      if (this.gugunCode) this.getGugunCenter(params);

      this.CLEAR_DONG_LIST();
      if (this.gugunCode) this.getDong(this.gugunCode);
    },

    // 검색 버튼 눌렸을 때
    searchApt() {
      this.SET_DONG(this.dongCode);
      this.SET_PAGE(1);

      this.areaName =
        this.sidoValue.text +
        " " +
        this.sigugunValue.text +
        " " +
        this.dongValue.text;

      const datas = {
        sidoCode: this.sidoCode,
        gugunCode: this.gugunCode,
        dong: this.dongCode,
        aptName: this.aptName,
        pg: this.page,
      };

      if (this.dongCode) this.getHouseList(datas);
    },
  },
};
</script>

<style>
.row + .row {
  margin-top: 0px;
  padding: 0 1rem 1rem 1rem;
}
</style>
