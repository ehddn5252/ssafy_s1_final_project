<template>
  <div>
    <b-row
      class="m-2"
      scrollable
      v-b-modal="`modal-house-${this.propIndex}`"
      variant="primary"
      @click="selectHouse0"
      @mouseover="colorChange(true)"
      @mouseout="colorChange(false)"
      :class="{ 'mouse-over-bgcolor': isColor }"
    >
      <b-col cols="2" class="text-center align-self-center"> </b-col>
      <b-col cols="10" class="align-self-center">
        [가격:{{ house.recentPrice }}(만원)]
        {{ house.apartmentName }}
        <div>{{ house.aroundStr }}</div>
      </b-col>
    </b-row>

    <b-modal
      size="xl"
      :id="`modal-house-${this.propIndex}`"
      hide-backdrop
      scrollable
      content-class="shadow"
      title="아파트 상세 거래 리스트"
    >
      <b-container>
        <h2>{{ house.apartmentName }}</h2>
        <b-row>
          <b-col>
            <house-detail
              v-for="(houseDeal, index) in deals"
              :key="index"
              :houseDeal="houseDeal"
              :propIndex="index"
            />
          </b-col>
        </b-row>
        <b-button @click="selectHouse5">더보기</b-button>
      </b-container>
    </b-modal>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import { mapState, mapMutations } from "vuex";
import HouseDetail from "@/components/house/HouseDetail";
export default {
  name: "HouseListItem",
  components: {
    HouseDetail,
  },

  data() {
    return {
      deals: null,
      isColor: false,
    };
  },

  watch: {
    houses: function () {
      this.deals = this.houseDeals;
    },
  },
  computed: {
    ...mapState(["houseDeals"]),
    houses() {
      return this.houseDeals;
    },
  },
  props: {
    house: Object,
    propIndex: Number,
  },
  methods: {
    ...mapActions(["detailHouse", "getDealList"]),
    ...mapMutations(["ADD_DEAL_COUNT"]),

    selectHouse0() {
      this.$store.commit("ADD_DEAL_COUNT", 0);
      this.$store.state.houseMapList = [this.house];
      console.log("this.house.aptCode");
      console.log(this.house.aptCode);
      this.getDealList(this.house.aptCode);
    },

    selectHouse5() {
      this.$store.state.houseMapList = [this.house];
      this.$store.commit("ADD_DEAL_COUNT", 5);
      console.log("this.house.aptCode");
      console.log(this.house.aptCode);
      this.getDealList(this.house.aptCode);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
