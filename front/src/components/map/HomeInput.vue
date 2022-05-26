<template>
  <div class="m-auto">
    <b-container>
      <b-row class="p-1 mx-auto">
        <span class="flex-fill"
          ><b-form-input
            autocomplete="off"
            id="search-input"
            type="search"
            class="form-control form-control p-4"
            placeholder="검색하고 싶은 장소를 입력하세요"
            @keyup="searchQuery"
            @keyup.enter="searchEnter"
            v-model="query"
            @focus="inputFocus"
          ></b-form-input
        ></span>
        <span>
          <v-btn
            id="search-apt"
            class="mx-3"
            fab
            dark
            small
            @click="searchEnter"
            color="primary"
          >
            <v-icon black> mdi-magnify </v-icon>
          </v-btn>
        </span>
      </b-row>
    </b-container>
    <b-container id="search-result" class="p-1" v-show="resultVisible && query">
      <b-list-group>
        <b-list-group-item v-if="resultSearch == ''">
          <b-row class="px-2 pb-1" align-h="center"
            ><b-icon-emoji-expressionless></b-icon-emoji-expressionless
          ></b-row>
          <b-row class="px-2" style="font-size: 12px" align-h="center">
            검색 결과가 없습니다.
          </b-row>
        </b-list-group-item>
        <b-list-group-item
          v-show="resultSearch"
          v-for="(item, index) in resultSearch.data"
          :key="index"
          button
          @click="resultClick(item)"
        >
          <b-row class="px-2">{{ item.place_name }}</b-row>
          <b-row class="p-2" style="color: grey; font-size: 12px">{{
            item.address_name
          }}</b-row>
        </b-list-group-item>
      </b-list-group>
    </b-container>
  </div>
</template>

<script>
export default {
  props: {
    resultSearch: Object,
    resultVisible: Boolean,
  },
  data() {
    return {
      query: "",
      rangeDeal: 20,
      rangeInfra: 0,
    };
  },
  methods: {
    searchQuery() {
      this.$emit("result-visible", true);
      this.$emit("search-query", this.query);
    },
    resultClick(position) {
      console.log("click!!!");
      this.query = position.place_name;
      this.$emit("result-click", {
        lat: position.y,
        lng: position.x,
      });
    },
    searchEnter() {
      if (!this.resultSearch || this.resultSearch.length == 0) return;
      this.$emit("result-click", {
        lat: this.resultSearch.data[0].y,
        lng: this.resultSearch.data[0].x,
      });
    },
    inputFocus() {
      this.$emit("result-visible", true);
      this.query = "";
    },
    inputBlur() {
      this.$emit("result-visible", false);
    },

    main() {
      window.location.href = "/";
    },
  },
};
</script>

<style scoped>
#search-apt {
  background-color: rgb(58, 58, 185);
}
#search-result {
  background: white;
}
#search-input {
  width: 350px;
  box-shadow: 0px 3px 3px -1px rgb(0 0 0 / 20%),
    0px 6px 4px 0px rgb(0 0 0 / 14%), 0px 1px 9px 0px rgb(0 0 0 / 12%);
}
#search-box {
  z-index: 1000;
  max-width: 370px;
  background: white;
}
#search-result {
  max-height: 300px;
  overflow: auto;
}
.mouseover-cursor {
  cursor: pointer;
}
</style>
