<template>
  <div>
    <b-row sm="3" class="mt-4 mb-4 text-center">
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
    <b-row sm="9">
      <b-container fluid="lg">
        <chart-Example :avgs="avgs" :names="names" :lats="lats" :lngs="lngs" />
      </b-container>
    </b-row>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import http from "@/api/http";
import ChartExample from "@/components/chart/ChartExample.vue";
const memberStore = "memberStore";
export default {
  name: "HouseSearchBar",
  components: {
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
    ...mapState([
      "sidos",
      "guguns",
      "dongs",
      "houses",
      "page",
      memberStore,
      ["userInfo"],
    ]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
    this.avgs = new Array();
    this.names = new Array();
    this.lats = new Array();
    this.lngs = new Array();
    this.info.dataKind = "sido";
    // if (true) {

    // }

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
      this.avgs = new Array();
      this.names = new Array();
      this.lats = new Array();
      this.lngs = new Array();

      if (this.info.dataKind == "sido") {
        http
          .get(`/avg/${this.info.dataKind}`)
          .then(({ data }) => {
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
        let params = {};
        let paramKey = "sido";
        if (this.info.dataKind == "dong") {
          paramKey = "gugun";
        }
        params[paramKey] = this.info.regionCode;
        http
          .get(`/avg/${this.info.dataKind}/specific`, { params })
          .then(({ data }) => {
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

    gugunChart() {
      if (this.$store.state.memberStore.userInfo.manager != "VIP") {
        alert("VIP 멤버십 가입 후 이용 가능합니다.");
        this.$router.replace({ name: "home" });
      }
      this.info.dataKind = "gugun";
      this.info.regionCode = this.sidoCode;
      this.CLEAR_DONG_LIST();
      console.log("gugunchart", this.info);
      if (this.sidoCode) this.getGugun(this.sidoCode);
      this.callAPI();
    },

    dongChart() {
      this.SET_DONG(this.dongCode);
      this.info.dataKind = "dong";
      this.info.regionCode = this.gugunCode;
      console.log("dongChart", this.info);

      this.callAPI();
    },
  },
};
</script>

<style></style>
