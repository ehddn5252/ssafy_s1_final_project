<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-orange">
      <b-icon icon="house-fill"></b-icon> Chart Service
    </h3>
    <b-row>
      <b-col>
        <chart-search-bar></chart-search-bar>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <div style="position: fixed">
          <b-row>
            <!-- <house-detail></house-detail> -->
          </b-row>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>
<script>
import ChartSearchBar from "@/components/chart/ChartSearchBar.vue";
export default {
  name: "ChartView",
  components: { ChartSearchBar },
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
