<template>
  <b-row>
    <b-col class="m-2" :class="{ 'mouse-over-bgcolor': isColor }">
      <b-col cols="2" class="text-center align-self-center">
        <b-img
          thumbnail
          src="https://picsum.photos/250/250/?image=58"
          alt="Image 1"
        ></b-img>
      </b-col>
      <b-col cols="10" class="align-self-center font-weight-bold">
        <!-- 이걸 누르면 상호 추가 -->

        {{ userInterest.areaName }}
      </b-col>
    </b-col>
    <b-col>
      <div>
        <b-button
          @click="showAroundInterest"
          scrollable
          v-b-modal="`modal-round-store-${this.propIndex}`"
          variant="primary"
          class="m-1"
          >주변상권</b-button
        >
      </div>
      <b-modal
        size="xl"
        :id="`modal-round-store-${this.propIndex}`"
        hide-backdrop
        scrollable
        content-class="shadow"
        title="주변상권 리스트"
      >
        <b-container>
          <h2>{{ userInterest.areaName }}</h2>
          <b-row>
            <b-col>
              <around-store-list></around-store-list>
            </b-col>
            <b-col>
              <interest-region-map></interest-region-map>
            </b-col>
          </b-row>
        </b-container>
      </b-modal>

      <b-button
        @click="showAptInterest"
        size="xl"
        v-b-modal="`modal-apart-${this.propIndex}`"
        variant="primary"
        class="m-1"
        >아파트</b-button
      >

      <b-modal
        size="xl"
        :id="`modal-apart-${this.propIndex}`"
        hide-backdrop
        content-class="shadow"
        title="`아파트 리스트`"
      >
        <b-container>
          <h2>{{ userInterest.areaName }}</h2>
          <b-row>
            <b-col>
              <house-list></house-list>
            </b-col>
            <b-col>
              <interest-region-map></interest-region-map>
            </b-col>
          </b-row>
        </b-container>
      </b-modal>

      <b-form
        @mouseover="colorChange(true)"
        @mouseout="colorChange(false)"
        @submit="deleteUserInterest"
      >
        <b-button type="submit" variant="danger" class="m-1">삭제</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import HouseList from "@/components/house/HouseList.vue";
import AroundStoreList from "@/components/around/AroundStoreList.vue";
import InterestRegionMap from "@/components/interestregion/InterestRegionMap.vue";
import { mapActions } from "vuex";
import http from "@/api/http";
export default {
  name: "InterestRegionListItem",
  data() {
    return {
      isColor: false,
    };
  },
  components: {
    HouseList,
    AroundStoreList,
    InterestRegionMap,
  },
  props: {
    userInterest: Object,
    propIndex: Number,
  },

  computed: {},
  methods: {
    ...mapActions([
      "detailAroundStore",
      "detailHouse",
      "getSidoHouseMap",
      "getAroundStoreList",
      "getHouseList",
    ]),

    deleteUserInterest() {
      console.log("in deleteUserInterest");

      console.log("params.userInterest.dongCode");
      console.log(this.userInterest.dongCode);
      console.log("params.userInterest.interestId");
      console.log(this.userInterest.interestId);
      const params = { interestId: this.userInterest.interestId };
      http
        .delete(`/interest/list`, { params })
        .then(({ data }) => {
          let msg = "삭제 처리시 문제가 발생했습니다.";
          if (data == "success") {
            msg = "삭제가 완료되었습니다.";
          }
          alert(msg);
        })
        .catch((error) => {
          console.log("delete error");
          console.log(error);
        });
    },

    showAroundInterest() {
      const datas = {
        sidoCode: this.userInterest.sidoCode,
        gugunCode: this.userInterest.sigugunCode,
        dongCode: this.userInterest.dongCode,
      };
      console.log("this.propIndex");
      console.log(this.propIndex);
      this.$store.state.mapList = [this.userInterest];
      this.detailAroundStore(this.userInterest);
      this.getAroundStoreList(datas);
    },

    // showApartInterest() {
    //   this.detailHouse(this.userInterest);

    //   const datas = {
    //     sidoCode: this.userInterest.sidoCode,
    //     gugunCode: this.userInterest.sigugunCode,
    //     dongCode: this.userInterest.dongCode,
    //   };

    //   this.$store.state.houseMapList = [this.userInterest];
    //   this.getHouseList(datas);
    // },

    showAptInterest() {
      // this.$store.state.aroundStores=[this.aroundStore.lat,this.aroundStore.log];
      console.log("listRow : ", this.userInterest);
      // this.$store.dispatch("getHouse", this.house);
      // this.$store.state.userInterestList = [this.userInterest];
      this.detailHouse(this.userInterest);

      const datas = {
        sidoCode: this.userInterest.sidoCode,
        gugunCode: this.userInterest.sigugunCode,
        dongCode: this.userInterest.dongCode,
      };
      this.getHouseList(datas);
      console.log("it is run");
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
