<template>
  <div>
    <b-row class="mt-4 mb-4 text-center">
      <!-- <b-col class="sm-3">
      <b-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col> -->
      <b-col class="sm-3">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunChart"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongChart"
        ></b-form-select>
      </b-col>
    </b-row>
    <!-- <b-row>
      <house-search-condition></house-search-condition>
    </b-row> -->
    <chart-Example :avgs="avgs" :names="names" :lats="lats" :lngs="lngs" />
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import http from "@/api/http";
// import HouseSearchCondition from "@/components/house/HouseSearchCondition.vue";
import ChartExample from "@/components/chart/ChartExample.vue";
export default {
  name: "HouseSearchBar",
  components: {
    // HouseSearchCondition,
    ChartExample,
  },
  data() {
    return {
      aptName: "",
      // key: "userid",
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      conditions: [],
      info: {
        dataKind: null,
        regionCode: null,
      },
      avgs: null,
      names: null,
      lats: null,
      lngs: null,
    };
  },
  computed: {
    ...mapState(["sidos", "guguns", "dongs", "houses", "page"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
    this.avgs = new Array();
    this.names = new Array();
    this.lats = new Array();
    this.lngs = new Array();
    this.info.dataKind = "sido";

    this.callAPI();
  },
  watch: {},
  methods: {
    ...mapActions(["getSido", "getGugun", "getDong"]),

    ...mapMutations([
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_DONG",
    ]),
    callAPI() {
      console.log("this.info.dataKind");
      console.log(this.info.dataKind);
      this.avgs = new Array();
      this.names = new Array();
      this.lats = new Array();
      this.lngs = new Array();

      if (this.info.dataKind == "sido") {
        console.log("this is null case");
        http
          .get(`/avg/${this.info.dataKind}`)
          .then(({ data }) => {
            console.log("data?");
            console.log(data);
            console.log("data length");
            console.log(data.length);
            for (let i = 0; i < data.length; ++i) {
              this.avgs.push(data[i].avg);
              this.names.push(data[i].name);
              this.lats.push(data[i].lat);
              this.lngs.push(data[i].lng);
            }
          })
          .catch((e) => {
            console.log("e");
            console.log(e);
          });
      } else {
        console.log("this is not null case");
        let params = {};
        let paramKey = "sido";
        if (this.info.dataKind == "dong") {
          paramKey = "gugun";
        }
        params[paramKey] = this.info.regionCode;
        http
          .get(`/avg/${this.info.dataKind}/specific`, { params })
          .then(({ data }) => {
            console.log("data?");
            console.log(data[0]);
            console.log(data.length);
            for (let i = 0; i < data.length; ++i) {
              this.avgs.push(data[i].avg);
              this.names.push(data[i].name);
              this.lats.push(data[i].lat);
              this.lngs.push(data[i].lng);
            }
          })
          .catch((e) => {
            console.log("e");
            console.log(e);
          });
      }
    },
    sidoList() {
      this.getSido();
    },
    // sidoChart() {
    //   this.CLEAR_GUGUN_LIST();
    //   this.info.dataKind = "sido";
    //   this.info.regionCode = null;
    //   // this.$set(this.info, "regionCode", null);
    //   console.log("sidochart", this.info);
    //   if (this.sidoCode) this.getGugun(this.sidoCode);
    //   this.callAPI();
    // },
    gugunChart() {
      this.info.dataKind = "gugun";
      this.info.regionCode = this.sidoCode;
      this.CLEAR_DONG_LIST();
      // this.$set(this.info, "regionCode", this.sidoCode);
      console.log("gugunchart", this.info);
      if (this.sidoCode) this.getGugun(this.sidoCode);
      this.callAPI();
    },

    dongChart() {
      this.SET_DONG(this.dongCode);
      this.info.dataKind = "dong";
      this.info.regionCode = this.gugunCode;
      // this.$set(this.info, "regionCode", this.gugunCode);
      console.log("dongChart", this.info);

      this.callAPI();
      // this.CLEAR_DONG_LIST();
    },
  },
};
</script>

<style></style>
