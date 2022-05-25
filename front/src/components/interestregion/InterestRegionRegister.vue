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
        let msg = "관심 지역이 등록되었습니다.";
        http
          .post(`/interest/insert`, {
            userId: this.userInfo.userId,
            sidoCode: this.sidoCode,
            sigugunCode: this.sigugunCode,
            dongCode: this.dongCode,
            areaName: this.areaName,
          })
          .then(({ data }) => {
            console.log("data?");
            console.log(data);
            this.userInterests = data;
            alert(msg);
          })
          .catch((e) => {
            console.log("e");
            console.log(e);
            msg = "지역을 동까지 선택해주세요.";
            alert(msg);
          });
      } else {
        console.log("dong Code is null");
      }
    },
  },
};
</script>

<style></style>
