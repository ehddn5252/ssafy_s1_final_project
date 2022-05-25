<template>
  <b-container class="bv-example-row mt-3">
    \
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listQna">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyQna"
          class="mr-2"
          >질문수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteQna"
          >질문삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${qna.qnano}.
          ${qna.subject} </h3><div><h6>${qna.userid}</div><div>${qna.regtime}<div>조회수: ${qna.hit}</div></h6></div>`"
          class="text-left mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left m-5">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import http from "@/api/http";

export default {
  name: "QnaDetail",

  data() {
    return {
      qna: {},
    };
  },
  computed: {
    message() {
      if (this.qna.comment) return this.qna.comment.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    this.$store.commit("SET_QNANO", this.$route.params.qnano);

    http.put(`/qna/hit/${this.$route.params.qnano}`).then(() => {
      http.get(`/qna/${this.$route.params.qnano}`).then(({ data }) => {
        this.qna = data[0];
      });
    });
  },
  methods: {
    listQna() {
      this.$router.push({ name: "qnaList" });
    },
    moveModifyQna() {
      this.$router.replace({
        name: "qnaModify",
        params: { qnano: this.qna.qnano },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteQna() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "qnaDelete",
          params: { qnano: this.qna.qnano },
        });
      }
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
