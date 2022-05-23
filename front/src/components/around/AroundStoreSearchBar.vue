<template>
  <div class="container">
    <div class="login-page">
      <b-row class="mt-4 mb-4 text-center">
        <b-col class="sm-3">
          <b-form-select
            ref="targetSido"
            v-model="sidoCode"
            :options="sidos"
            @change="gugunList"
          ></b-form-select>
        </b-col>
        <b-col class="sm-3">
          <b-form-select
            ref="targetGugun"
            v-model="gugunCode"
            :options="guguns"
            @change="dongList"
          ></b-form-select>
        </b-col>
        <b-col class="sm-3">
          <b-form-select
            ref="targetDong"
            v-model="dongCode"
            :options="dongs"
            @change="searchAroundStore"
          ></b-form-select>
        </b-col>
      </b-row>
      <interest-region-register
        :sidoCode="sidoCode"
        :sigugunCode="gugunCode"
        :dongCode="dongCode"
        :areaName="areaName"
      ></interest-region-register>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import InterestRegionRegister from "@/components/interestregion/InterestRegionRegister.vue";

export default {
  components: { InterestRegionRegister },
  name: "AroundStoreSearchBar",
  data() {
    return {
      mySidos: { sidoName: null, sidoCode: null },
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      areaName: null,
    };
  },
  computed: {
    ...mapState(["sidos", "guguns", "dongs"]),
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
      "getSidoAroundMap",
      "getSido",
      "getGugun",
      "getDong",
      "getHouse",
      "getAroundStoreList",
    ]),
    ...mapMutations(["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST", "CLEAR_DONG_LIST"]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchAroundStore() {
      this.areaName =
        this.sidoValue.text +
        " " +
        this.sigugunValue.text +
        " " +
        this.dongValue.text;

      const datas = {
        sidoCode: this.sidoCode,
        gugunCode: this.gugunCode,
        dongCode: this.dongCode,
      };
      if (this.dongCode) this.getAroundStoreList(datas);
    },
  },
};
</script>

<style></style>
