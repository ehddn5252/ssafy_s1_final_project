<template>
  <div class="content">
    <section class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>마이페이지</h2>
          <ol>
            <li><a href="index.jsp">Home</a></li>
            <li>마이페이지</li>
          </ol>
        </div>
      </div>
    </section>
    <!-- <h3 class="underline-steelblue">
      <b-icon icon="person-lines-fill"></b-icon> AroundStore Service
    </h3> -->
    <b-container class="bv-example-row mt-3 text-center">
      <b-row>
        <b-col>
          <around-store-search-bar></around-store-search-bar>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="6" align="left">
          <around-store-list />
        </b-col>
        <div style="postion: fixed">
          <b-col>
            <b-row>
              <around-store-map></around-store-map>
            </b-row>
            <b-row>
              <around-store-detail></around-store-detail>
            </b-row>
          </b-col>
        </div>
      </b-row>
    </b-container>
    <!-- <router-view></router-view> -->
  </div>
</template>

<script>
import AroundStoreSearchBar from "@/components/around/AroundStoreSearchBar.vue";
import AroundStoreDetail from "@/components/around/AroundStoreDetail.vue";
import AroundStoreList from "@/components/around/AroundStoreList.vue";
import AroundStoreMap from "@/components/around/AroundStoreMap.vue";
import { mapMutations } from "vuex";
export default {
  name: "AroundStoreView",
  components: {
    AroundStoreSearchBar,
    AroundStoreDetail,
    AroundStoreList,
    AroundStoreMap,
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
#app > .content {
  position: relative;
  top: 100px;
}
/* div.content {} */
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
