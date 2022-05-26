<template>
  <tbody>
    <!-- 하위 component인 ListRow에 데이터 전달(props) -->
    <board-list-item
      v-for="article in articles"
      :key="article.articleno"
      v-bind="article"
    />
  </tbody>
</template>

<script>
import http from "@/api/http";
import BoardListItem from "@/components/board/item/BoardListItem";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: [],
    };
  },
  created() {
    http.get(`/board/popular`).then(({ data }) => {
      this.articles = data;
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
