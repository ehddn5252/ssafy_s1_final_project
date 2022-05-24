<template>
  <b-container v-if="houseDeal" class="bv-example-row">
    <!-- <b-row>
      <b-col
        ><h3>no: {{ houseDeal.no }}</h3></b-col
      >
    </b-row> -->
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          ><div>거래가 : {{ houseDeal.dealAmount }}</div>
          <div>거래연도: {{ houseDeal.dealYear }}</div>
          <div>층 수 : {{ houseDeal.floor }}</div>
          <div>
            타입 : {{ houseDeal.area }} m<sup>2</sup>({{
              Math.round(houseDeal.area * 0.3025)
            }}평)
          </div>
        </b-alert>
      </b-col>
      <!-- </b-row> -->
      <!-- <b-row>
      <b-col>
        <b-alert show variant="primary"
          >
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info"> </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info"
          
        </b-alert> -->
      <!-- </b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(["house"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  props: {
    houseDeal: Object,
    propIndex: Number,
  },
};
</script>

<style></style>
