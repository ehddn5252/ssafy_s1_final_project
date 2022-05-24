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
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          v-model="dongCode"
          :options="dongs"
          @change="searchApt"
        ></b-form-select>
      </b-col>
    </b-row>
    <b-row>
      <b-input-group class="mt-3">
        <b-form-input
          v-model="aptName"
          @keydown.enter="searchApt"
        ></b-form-input>
        <b-input-group-append>
          <b-button variant="info" @click="searchApt">아파트 검색</b-button>
        </b-input-group-append>
      </b-input-group>
    </b-row>
    <!-- <b-row>
      <house-search-condition></house-search-condition>
    </b-row> -->
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
// import HouseSearchCondition from "@/components/house/HouseSearchCondition.vue";

export default {
  name: "HouseSearchBar",
  components: {
    // HouseSearchCondition,
  },
  data() {
    return {
      aptName: "",
      // key: "userid",
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      conditions: [],
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
  },
  methods: {
    ...mapActions([
      "getSidoHouseMap",
      "getSido",
      "getGugun",
      "getHouseList",
      "getDong",
      "getHouse",
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
    sidoList() {
      this.getSido();
    },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    // searchApt() {
    //   if (this.gugunCode) this.getHouseList(this.gugunCode);
    // },
    dongList() {
      this.CLEAR_DONG_LIST();
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    // searchApt() {
    //   const datas = {
    //     sidoCode: this.sidoCode,
    //     gugunCode: this.gugunCode,
    //     dongCode: this.dongCode,
    //   };

    //   if (this.dongCode) this.getHouseList(datas);
    // },

    // 검색 버튼 눌렸을 때
    searchApt() {
      this.SET_DONG(this.dongCode);
      this.SET_PAGE(1);
      // event.preventDefault();
      // if (this.aptName == "") {
      //   alert("모든 목록 조회!!!");
      // }

      const datas = {
        sidoCode: this.sidoCode,
        gugunCode: this.gugunCode,
        dong: this.dongCode,
        aptName: this.aptName,
        pg: this.page,
      };

      if (this.dongCode) this.getHouseList(datas);

      // http.get(`/map/list?pg=1&aptName=${this.aptName}`).then(({ data }) => {
      //   console.log(data);
      //   this.SET_HOUSE_LIST(data.aptlist);
      //   this.SET_NAVIGATOR(data.navigation.navigator);
      // });
    },

    // linkGen(pageNum) {
    //   return pageNum === 1 ? "?" : `?page=${pageNum}`;
    // },
  },
};
</script>

<style></style>
