<template>
  <b-container
    v-if="userInterests && userInterests.length != 0"
    class="bv-example-row mt-3"
  >
    <interest-region-list-item
      v-for="(userInterest, index) in userInterests"
      :key="index"
      :propIndex="index"
      :userInterest="userInterest"
    >
    </interest-region-list-item>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>관심 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import InterestRegionListItem from "@/components/interestregion/item/InterestRegionListItem.vue";
import http from "@/api/http";
import { mapState } from "vuex";
const memberStore = "memberStore";
console.log("hhh");
export default {
  name: "InterestRegionList",
  components: {
    InterestRegionListItem,
  },
  data() {
    return {
      userInterests: [],
    };
  },
  created() {
    console.log("created");
    const params = {
      userId: "ssafy", //this.userInfo.userId,
    };
    http
      .get(`/interest/list`, { params })
      .then(({ data }) => {
        console.log("data?");
        console.log(data);
        this.userInterests = data;
      })
      .catch((e) => {
        console.log("e");
        console.log(e);
      });
  },
  computed: {
    ...mapState(["houses"]),
    ...mapState(memberStore, ["userInfo"]),
    houses() {
      return this.$store.state.houses;
    },
  },

  methods: {
    // moveWrite(){
    //   this.$router.push({ name: "InterestRegionInsert" });
    // }
  },
};
</script>

<style></style>
