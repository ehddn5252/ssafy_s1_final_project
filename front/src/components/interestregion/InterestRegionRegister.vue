<template>
  <b-container>
    <span @click="checkIsIn">
      <b-icon
        icon="star-fill"
        style="color: rgb(163, 163, 60)"
        v-if="!this.isLike"
      ></b-icon>
      <b-icon icon="star" style="color: rgb(163, 163, 60)" v-else></b-icon>
    </span>
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
    displayByValue: function () {
      return this.isLike;
    },
  },

  watch: {
    dongCode: function () {
      console.log("created check");
      const params = {
        dongCode: this.dongCode,
        userId: this.userInfo.userId,
      };
      http
        .get(`/interest/list/check`, { params })
        .then(({ data }) => {
          console.log("data?");
          console.log(data);
          this.interestLength = data.length;
          if (data.length == 0) {
            console.log("data.length == 0");
            this.isLike = true;
          } else {
            console.log("data.length == 1");
            this.isLike = false;
          }
        })
        .catch((e) => {
          console.log("e");
          console.log(e);
        });
    },
  },

  data() {
    return { interestLength: 1, isLike: true };
  },
  props: {
    sidoCode: String,
    sigugunCode: String,
    dongCode: String,
    areaName: String,
  },

  methods: {
    isLikeCheck() {
      if (this.interestLength == 0) {
        return false;
      } else {
        return true;
      }
    },
    checkIsIn() {
      const params = {
        dongCode: this.dongCode,
        userId: this.userInfo.userId,
      };
      if (this.dongCode != null) {
        http
          .get(`/interest/list/check`, { params })
          .then(({ data }) => {
            this.interestLength = data.length;
            if (this.interestLength == 0) {
              console.log("isLike is" + this.isLike);
              this.registerInsert();
              this.isLike = false;
            } else {
              console.log("isLike is" + this.isLike);
              this.isLike = true;
              this.deleteInterest(data[0].interestId);
            }
          })
          .catch((e) => {
            console.log("e");
            console.log(e);
          });
      } else {
        alert("동까지 모두 선택해주세요.");
      }
    },

    deleteInterest(interestId) {
      const params = { interestId: interestId };
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

    registerInsert() {
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
          console.log("data:", data);
          alert(msg);
        })
        .catch((e) => {
          console.log("e");
          console.log(e);
          msg = "지역을 동까지 선택해주세요.";
          alert(msg);
        });
    },
  },
};
</script>

<style></style>
