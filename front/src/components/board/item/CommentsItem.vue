<template>
  <b-container class="bv-example-row">
    <b-row class="mt-3">
      <b-col cols="11" class="bg-light p-2 pl-5 text-left">
        <span>
          <h6>
            <div>{{ this.userid }}</div>
            <div>{{ regtime }}</div>
          </h6></span
        >
        <div>
          {{ message }}
        </div>
      </b-col>
      <b-col v-show="isVal" class="p-2"
        ><b-button variant="danger" @click="deleteComment">X</b-button></b-col
      >
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import moment from "moment";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  name: "CommentsItem",
  props: {
    commentsno: Number,
    userid: String,
    regtime: String,
    content: String,
  },

  created() {
    console.log(this.commentsno);
    console.log("created");
    console.log(this.userid);
    console.log(this.userInfo.userId);
    // console.log(this.$store.state.memberStore.userInfo.userId);
    this.writer = this.$store.state.memberStore.userInfo.userId;
    if (this.userid == this.$store.state.memberStore.userInfo.userId) {
      this.isVal = true;
    } else {
      this.isVal = false;
    }
  },

  datas: {
    isVal: true,
  },
  computed: {
    message() {
      if (this.content) return this.content.split("\n").join("<br>");
      return "";
    },
    ...mapState(memberStore, ["userInfo"]),
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
  methods: {
    deleteComment() {
      http.delete(`/comments/${this.commentsno}`).then(() => {
        this.$store.dispatch("getComments", this.$store.state.articleno);
      });
    },
  },
};
</script>

<style></style>
