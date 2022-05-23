<template>
  <b-container class="bv-example-row">
    <b-row class="mt-4 mb-4 text-center">
      <b-col v-if="comments.length">
        <b-col>
          <comments-item
            v-for="comment in comments"
            :key="comment.commentsno"
            v-bind="comment"
          />
        </b-col>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import CommentsItem from "@/components/board/item/CommentsItem";
import http from "@/api/http";

export default {
  name: "CommentsList",
  props: {
    articleno: Number,
  },
  components: {
    CommentsItem,
  },
  method: {
    updateComments() {
      console.log("변경~~~~~~~~");
      http.get(`/comments/${this.articleno}`).then(({ data }) => {
        console.log(data);
        this.comments = data;
      });
    },
  },
  computed: {
    ...mapState(["comments"]),
  },
  mounted() {
    this.$store.dispatch("getComments", this.articleno);
  },
};
</script>

<style></style>
