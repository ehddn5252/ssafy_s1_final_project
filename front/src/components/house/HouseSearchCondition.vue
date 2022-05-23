<template>
  <b-row class="mt-4 mb-4 text-center">
    <div class="condition-container col-sm-11 flex-column" style="margin: auto">
      <div class="d-flex align-items-center justify-content-between">
        <b style="color: #000080">조건 추가</b>&emsp;

        <b-form-select v-model="category" :options="categories"></b-form-select>
        까지&emsp;
        <b-form-select
          v-model="transportation"
          :options="transportations"
        ></b-form-select>
        (으)로&emsp;
        <b-form-input v-model="time" placeholder="시간"></b-form-input>
        분 이내
        <div class="addBtn-container">
          <button id="addCdtBtn" @click="addCodition">
            <img src="img/toDoBtnIcon/enterBtn.png" />
          </button>
        </div>
      </div>
      <div class="d-none mt-2" id="check-msg" style="width: 100%; color: red">
        거리를 입력하세요.
      </div>
    </div>
    <div class="col-sm-11" style="margin: auto">
      <!-- 추가된 조건들 -->
      <div id="around-conditions"></div>
    </div>
  </b-row>
</template>

<script>
import { mapMutations } from "vuex";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      conditions: [],
      category: null,
      categories: [
        {
          label: "교통",
          options: [
            { value: "버스정류장", text: "버스정류장" },
            { value: "지하철역", text: "지하철역" },
          ],
        },
        {
          label: "학교",
          options: [
            { value: "유치원", text: "유치원" },
            { value: "초등학교", text: "초등학교" },
            { value: "중학교", text: "중학교" },
            { value: "고등학교", text: "고등학교" },
          ],
        },
        {
          label: "편의시설",
          options: [
            { value: "편의점", text: "편의점" },
            { value: "마트", text: "마트" },
            { value: "스타벅스", text: "스타벅스" },
            { value: "맥도날드", text: "맥도날드" },
          ],
        },
        {
          label: "응급시설",
          options: [
            { value: "응급실", text: "응급실" },
            { value: "병원", text: "병원" },
          ],
        },
      ],
      transportation: null,
      transportations: [
        { value: "도보", text: "도보" },
        { value: "차량", text: "차량" },
      ],
      time: null,
    };
  },
  computed: {
    // ...mapState(["sidos", "guguns", "dongs", "houses"]),
  },
  methods: {
    ...mapMutations(["SET_CONDITIONS"]),
    addCodition() {
      this.conditions.push({
        time: this.time,
        transportation: this.transportation,
        category: this.category,
      });
      this.time = "";
      this.transportation = "";
      this.category = "";
      this.SET_CONDITIONS(this.conditions);
    },
  },
};
</script>

<style></style>
