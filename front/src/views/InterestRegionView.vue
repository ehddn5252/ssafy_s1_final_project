<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-steelblue">
      <b-icon icon="person-lines-fill"></b-icon> UserInterest Service
    </h3>
    <b-row>
      <b-col>
        <chart-view />
        <!-- <interest-region-search-bar></interest-region-search-bar> -->
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="6" align="left">
        <interest-region-list></interest-region-list>
      </b-col>
      <b-col>
        <interest-region-popular-list />
      </b-col>
      <b-col>
        <b-row>
          <!-- <interest-region-map></interest-region-map> -->
        </b-row>
        <b-row>
          <interest-region-detail></interest-region-detail>
        </b-row>
      </b-col>

      <b-col>
        <chart-template :dataKind="dataKind" :regionCode="regionCode" />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import InterestRegionList from "@/components/interestregion/InterestRegionList.vue";
// import InterestRegionMap from "@/components/interestregion/InterestRegionMap.vue";
import InterestRegionDetail from "@/components/interestregion/InterestRegionDetail.vue";
import InterestRegionPopularList from "@/components/interestregion/InterestRegionPopularList.vue";
import ChartTemplate from "@/components/chart/ChartTemplate.vue";
import { mapMutations } from "vuex";
export default {
  name: "InterestRegionView",
  components: {
    InterestRegionList,
    // InterestRegionMap,
    InterestRegionDetail,
    InterestRegionPopularList,
    ChartTemplate,
  },
  datas: {
    dataKind: null,
    regionCode: null,
  },
  methods: {
    ...mapMutations([
      "CLEAR_AROUND_STORES_LIST",
      "this.CLEAR_AROUND_STORES_LIST",
      "CLEAR_MAP_LIST",
      "CLEAR_HOUSE_MAP_LIST",
    ]),
  },
  created() {
    this.dataKind = "sido";
    // this.regionCode = "11110";
    console.log("InterestRegionView");
    console.log("dataKind");
    console.log(this.dataKind);
    console.log("created");
    this.$store.commit("CLEAR_HOUSES_LIST");
    this.$store.commit("CLEAR_AROUND_STORES_LIST");
    this.$store.commit("CLEAR_MAP_LIST");
    this.$store.commit("CLEAR_HOUSE_MAP_LIST");
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
