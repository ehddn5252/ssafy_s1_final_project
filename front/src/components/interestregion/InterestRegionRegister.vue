<template>
  <b-container>
    <b-row>
      <b-button @click="registerInsert">관심 지역 추가</b-button>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";
import http from "@/api/http";
export default {
  name: "InterestRegionRegister",

  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  props: {
    sidoCode: String,
    sigugunCode: String,
    dongCode: String,
    areaName: String,
  },
  methods: {
    registerInsert() {
      console.log("===========");
      console.log(this.userInfo.userId);
      console.log(this.sidoCode);
      console.log(this.sigugunCode);
      console.log(this.dongCode);
      console.log(this.areaName);
      // const params = {
      //   userId: "ssafy", //this.userInfo.userId
      //   sidoCode: this.sidoCode,
      //   sigugunCode: this.sigugunCode,
      //   dongCode: this.dongCode,
      //   areaName: this.areaName,
      // };
      if (this.dongCode != null) {
        http
          .post(`/interest/insert`, {
            userId: "ssafy", //this.userInfo.userId
            sidoCode: this.sidoCode,
            sigugunCode: this.sigugunCode,
            dongCode: this.dongCode,
            areaName: this.areaName,
          })
          .then(({ data }) => {
            console.log("data?");
            console.log(data);
            this.userInterests = data;
          })
          .catch((e) => {
            console.log("e");
            console.log(e);
          });
      } else {
        console.log("dong Code is null");
      }
    },
  },
};
</script>

<style></style>
