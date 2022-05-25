<template>
  <ul class="menu align-items-center justify-content-space-between">
    <div>
      <router-link to="/">
        <img
          src="../../assets/Logo.jpg"
          class="ml-5"
          style="object-fit: contain; width: 110px"
        />
      </router-link>
    </div>
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    <div
      class="menu-indicator"
      :style="{ left: positionToMove, width: sliderWidth }"
    ></div>
    <li
      class="menu-item"
      v-for="link in links"
      :key="link.id"
      @click="sliderIndicator(link.id)"
      :ref="'menu-item_' + link.id"
    >
      <router-link :to="{ name: link.name }" class="menu-link">
        <i class="menu-icon" :class="link.icon"></i>
        <span>{{ link.text }}</span>
      </router-link>

      <!-- <a
        href="#"
        class="menu-link"
        :class="link.id === selectedIndex ? 'active' : null"
      >
      </a> -->
    </li>
    <div class="p-2 mt-1 ml-5">
      <!-- <v-text-field
        label="아파트 찾기"
        prepend-inner-icon="mdi-magnify"
        placeholder="검색할 장소를 입력"
        filled
        rounded
        dense
        clearable
      ></v-text-field> -->

      <nav-search
        @search-query="searchQuery"
        @result-click="moveToMap"
        @result-visible="changeResultVisible"
        :resultSearch="resultSearch"
        :resultVisible="resultVisible"
      ></nav-search>
    </div>
    <b-navbar-nav class="ml-auto p-2" v-if="userInfo">
      <b-nav-item class="align-self-center"
        ><b-avatar
          variant="primary"
          v-text="userInfo ? userInfo.userId.charAt(0).toUpperCase() : ''"
        ></b-avatar
        >{{ userInfo.username }}({{ userInfo.userId }})님
        환영합니다.</b-nav-item
      >

      <b-nav-item class="align-self-center"
        ><router-link :to="{ name: 'mypage' }" class="link align-self-center"
          >내정보보기</router-link
        ></b-nav-item
      >
      <b-nav-item class="link align-self-center" @click.prevent="onClickLogout"
        >로그아웃</b-nav-item
      >
    </b-navbar-nav>
    <b-navbar-nav class="p-2 mr-3" v-else>
      <b-nav-item-dropdown right>
        <template #button-content>
          <b-icon icon="people" font-scale="2"></b-icon>
        </template>
        <b-dropdown-item href="#"
          ><router-link :to="{ name: 'signUp' }" class="link"
            ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
          ></b-dropdown-item
        >
        <b-dropdown-item href="#"
          ><router-link :to="{ name: 'signIn' }" class="link"
            ><b-icon icon="key"></b-icon> 로그인</router-link
          ></b-dropdown-item
        >
      </b-nav-item-dropdown>
    </b-navbar-nav>
  </ul>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import NavSearch from "@/components/layout/NavSearch.vue";
const memberStore = "memberStore";
export default {
  components: {
    NavSearch,
  },
  data() {
    return {
      ps: null,
      resultSearch: {},
      resultVisible: false,
      sliderPosition: 0,
      selectedElementWidth: 0,
      selectedIndex: 0,
      view: {
        topOfPage: true,
      },
      links: [
        {
          id: 1,
          icon: "fab fa-react",
          text: "홈",
          name: "home",
        },
        {
          id: 2,
          icon: "fab fa-angular",
          text: "아파트찾기",
          name: "map",
        },
        {
          id: 3,
          icon: "fab fa-vuejs",
          text: "주변상권",
          name: "store",
        },
        {
          id: 4,
          icon: "fab fa-html5",
          text: "관심지역",
          name: "interestregion",
        },
        {
          id: 5,
          icon: "fab fa-html5",
          text: "차트",
          name: "chart",
        },
        {
          id: 6,
          icon: "fab fa-html5",
          text: "게시판",
          name: "boardList",
        },
        {
          id: 7,
          icon: "fab fa-html5",
          text: "문의사항",
          name: "qna",
        },
      ],
    };
  },
  mounted() {
    if (!(window.kakao && window.kakao.maps)) {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" +
        "fdf614748efadd63bf7ce73b5ddad4f1" +
        "&libraries=services,clusterer";
      document.head.appendChild(script);
    } else {
      this.initMap();
    }
  },

  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    initMap() {
      this.ps = new kakao.maps.services.Places();
    },
    searchQuery(query) {
      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
      if (query) {
        this.ps.keywordSearch(query, this.placesSearchCB);
      }
    },
    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // mapinput에 보내기
        this.resultSearch = {
          data,
          pagination,
        };
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        this.resultSearch = {};
        return;
      } else if (status === kakao.maps.services.Status.ERROR) {
        alert("검색 결과 중 오류가 발생했습니다.");
        return;
      }
    },
    moveToMap(position) {
      // this.resultVisible = false;
      console.log("position", position);
      this.$router.push({
        name: "map",
        params: { lat: position.lat, lng: position.lng },
      });
    },
    searchEnter() {
      if (this.resultSearch == {}) return;
      this.$emit("result-click", {
        lat: this.resultSearch.data[0].y,
        lng: this.resultSearch.data[0].x,
      });
    },
    inputFocus() {
      this.$emit("result-visible", true);
      this.query = "";
    },
    resultClick(position) {
      console.log("click!!!");
      this.query = position.place_name;
      this.$emit("result-click", {
        lat: position.y,
        lng: position.x,
      });
    },
    changeResultVisible(bool) {
      this.resultVisible = bool;
    },
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
    handleScroll() {
      if (window.pageYOffset > 0) {
        if (this.view.topOfPage) this.view.topOfPage = false;
      } else {
        if (!this.view.topOfPage) this.view.topOfPage = true;
      }
    },
    sliderIndicator(id) {
      let el = this.$refs[`menu-item_${id}`][0];
      this.sliderPosition = el.offsetLeft;
      this.selectedElementWidth = el.offsetWidth;
      this.selectedIndex = id;
    },
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),

    positionToMove() {
      return this.sliderPosition + "px";
    },
    sliderWidth() {
      return this.selectedElementWidth + "px";
    },
    // beforeMount() {
    //   window.addEventListener("scroll", this.handleScroll);
    // },
  },
};
</script>

<style>
.v-text-field.v-text-field--enclosed .v-text-field__details {
  display: none;
}
:root {
  --active-color: #131f86;
  --link-text-color: #f1faee;
  --menu-background-color: #1d3557;
  --active-background-color: #132238;
}

/* ul */
.menu {
  padding: 0;
  margin: 0;
  position: fixed;
  top: 0px;
  /* background-color: var(--menu-background-color); */
  background-color: white;
  display: inline-flex;
  border-radius: 4px;
  list-style-type: none;
  width: 100%;
  min-height: 80px;
  box-shadow: 0 0 4px black;
  z-index: 10;
  justify-content: space-between;
}
.menu * :hover {
  text-decoration: none;
}

/* li */
.menu-item {
  display: inline-flex;
}

/* a */
.menu-link {
  padding: 0.75rem 1rem;
  display: inline-flex;
  align-items: center;
  color: black;
  font-weight: bold;
  text-decoration: none;
}

/* .menu-link:hover,
.menu-link.active {
  color: var(--active-color);
  background-color: #c1cbd9;
} */

/* icon */
.menu-icon {
  height: 1.5rem;
  width: 1.5rem;
  justify-content: center;
  align-items: center;
  display: inline-flex;
  margin-right: 0.2rem;
}
/* slider */
.menu-indicator {
  position: absolute;
  height: 0.25rem;
  background-color: var(--active-color);
  bottom: 0;
  left: 0;
  margin: auto;
  width: 3rem;
  transition: all ease 0.5s;
}
</style>
