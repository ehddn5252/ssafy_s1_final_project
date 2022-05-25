<template>
  <b-tr>
    <!-- <b-td>{{ qnano }}</b-td> -->
    <b-th style="color: rgb(145, 145, 145)"> Q </b-th>
    <b-td class="qnaTitle text-left">
      <router-link :to="{ name: 'qnaDetail', params: { qnano: qnano } }">{{
        subject
      }}</router-link>
    </b-td>
    <b-td v-if="this.comment"> <span class="yesComment">답변완료</span></b-td>
    <b-td v-else></b-td>
    <!-- <b-td>{{ hit }}</b-td>
    <b-td>{{ userid }}</b-td>
    <b-td>{{ regtime | dateFormat }}</b-td> -->
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "QnaListItem",
  props: {
    qnano: Number,
    userid: String,
    subject: String,
    hit: Number,
    regtime: String,
    comment: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style>
.yesComment {
  background: rgb(33, 168, 33);
  color: white;
  padding: 5px;
  border-radius: 5px;
}
.table th,
.table td {
  vertical-align: middle;
}
tr {
  height: 80px;
}
.qnaTitle * {
  font-weight: bold;
  color: rgb(70, 70, 70);
}
</style>
