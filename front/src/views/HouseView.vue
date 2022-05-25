<template>
  <b-container class="content bv-example-row mt-3 text-center">
    <h3 class="underline-orange">
      <b-icon icon="house-fill"></b-icon> House Service
    </h3>
    <b-row>
      <b-col>
        <house-search-bar></house-search-bar>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="6" align="left">
        <house-list />
      </b-col>

      <b-col>
        <div style="position: fixed">
          <b-row>
            <house-map></house-map>
          </b-row>
          <b-row>
            <house-detail></house-detail>
          </b-row>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import HouseMap from "@/components/house/HouseMap.vue";
import { mapMutations } from "vuex";
export default {
  name: "HouseView",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
    HouseMap,
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
    console.log("created");
    this.$store.commit("CLEAR_HOUSES_LIST");
    this.$store.commit("CLEAR_AROUND_STORES_LIST");
    this.$store.commit("CLEAR_MAP_LIST");
    this.$store.commit("CLEAR_HOUSE_MAP_LIST");
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>
