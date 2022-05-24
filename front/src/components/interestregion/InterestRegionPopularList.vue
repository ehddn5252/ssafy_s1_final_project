<template>
  <b-container class="bv-example-row mt-3">
    <b-row><h4>인기 지역 순위</h4></b-row>
    <b-row>
      <b-col v-if="interestRegions.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>지역명</b-th>
              <b-th>관심 등록 수</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <interest-region-popular-list-item
              v-for="(interestRegion, index) in interestRegions"
              :key="index"
              :propIndex="index"
              :interestRegion="interestRegion"
            />
          </tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import InterestRegionPopularListItem from "@/components/interestregion/item/InterestRegionPopularListItem";

export default {
  name: "InterestRegionPopularList",
  components: {
    InterestRegionPopularListItem,
  },
  data() {
    return {
      interestRegions: [],
    };
  },
  created() {
    http.get(`/interest/popular/list`).then(({ data }) => {
      for (let i = 0; i < data.length; ++i) {
        console.log("data[i]");
        console.log(data[i].interestId);
      }
      this.interestRegions = data;
      console.log("this.interestRegions");
      console.log(this.interestRegions);
    });
  },
  methods: {},
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
