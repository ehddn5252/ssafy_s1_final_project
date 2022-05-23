<template>
  <b-container v-if="userInterest" class="bv-example-row">
    <b-row>
      <b-col
        ><h3>{{ userInterest.bizesNm }}</h3></b-col
      >
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="warning"
          >주소 : {{ userInterest.areaName }}</b-alert
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "InterestRegionDetail",
  computed: {
    ...mapState(["userInterest"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
};
</script>

<style></style>
