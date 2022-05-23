<template>
  <div>
    <b-row
      class="m-2"
      scrollable
      v-b-modal="`modal-house-${this.propIndex}`"
      variant="primary"
      @click="selectHouse"
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
              v-for="(houseDeal, index) in houseDeals"
              :key="index"
              :houseDeal="houseDeal"
              :propIndex="index"
            />
          </b-col>
        </b-row>
      </b-container>
    </b-modal>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import http from "@/api/http";
import HouseDetail from "@/components/house/HouseDetail";
export default {
  name: "HouseListItem",
  components: {
    HouseDetail,
  },

  data() {
    return {
      isColor: false,
      houseDeals: null,
    };
  },
  props: {
    house: Object,
    propIndex: Number,
  },
  methods: {
    ...mapActions(["detailHouse"]),
    selectHouse() {
      console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      console.log("333333");

      this.$store.state.houseMapList = [this.house];
      console.log("44444");

      this.detailHouse(this.house);
      console.log("aptCode");
      console.log(this.house.aptCode);
      console.log("aptCode");
      const params = {
        dongCode: this.house.dongCode,
        aptCode: this.house.aptCode,
      };
      http
        .get(`/map/aptDeal`, { params })
        .then(({ data }) => {
          console.log("3. result 출력");
          console.log("datas");
          console.log(data);
          this.houseDeals = data;
          //commit("CLEAR_AROUND_STORES_LIST");
          //commit("SET_HOUSE_LIST", data);
        })
        .catch((error) => {
          console.log("error");
          console.log(error);
        });
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
