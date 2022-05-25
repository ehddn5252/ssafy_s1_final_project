<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
      :propIndex="index"
    />
    <div
      class="mx-3 mt-3 row justify-content-center"
      @click="clickPage"
      v-html="`${navigator}`"
    ></div>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState, mapMutations, mapActions } from "vuex";
export default {
  name: "HouseList",
  components: {
    // ...mapState(["house"]),
    HouseListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(["houses", "navigator", "page", "dongCode"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },

  methods: {
    ...mapActions(["getHouseList"]),
    ...mapMutations(["SET_PAGE"]),
    morePages() {
      this.maxPage += 5;
    },
    clickPage(e) {
      if (e.target.classList.contains("page-link")) {
        this.SET_PAGE(e.target.name);
        console.log(this.page);

        const datas = {
          sidoCode: this.sidoCode,
          gugunCode: this.gugunCode,
          dong: this.dongCode,
          aptName: this.aptName,
          pg: this.page,
        };

        if (this.dongCode) this.getHouseList(datas);
      }
    },
  },
};
</script>

<style></style>
