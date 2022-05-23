import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http";
// import tmap from "@/api/tmap";
import memberStore from "@/store/modules/memberStore.js";
// import { findApt } from "@/api/findApt";

// import axios from "axios";

import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    memberStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
  state: {
    sidos: [{ value: null, text: "시도 선택" }],
    guguns: [{ value: null, text: "구군 선택" }],
    dongs: [{ value: null, text: "동 선택" }],
    houses: [],
    house: null,
    avgs: [],
    circles: [],
    aroundStore: null,
    aroundStores: [],
    checkedStore: null,
    checkedStoreList: [],
    environs: [],
    environ: null,
    mapList: [],
    userInterest: null,
    userInterests: [],
    userInterestMapList: [],

    todos: [
      // { title: '할 일1', completed: false },
      // { title: '할 일2', completed: false },
    ],
    comments: [],
    qnano: null,
    // house map
    houseMapList: [],
    checkedHouse: [],
    checkodMapList: [],
    // 아파트 조건 (도보 몇 분 이내 ...)
    aptConditions: [],
  },
  getters: {
    aroundStoreLatLon(state) {
      var arr = new Array(state.mapList.length);
      for (var i = 0; i < arr.length; ++i) {
        var latlon = [state.mapList[i].lat, state.mapList[i].lon];
        arr[i] = latlon;
      }
      return arr;
    },

    userInterestLatLon(state) {
      var arr = new Array(state.userInterestMapList.length);
      for (var i = 0; i < arr.length; ++i) {
        var latlon = [
          state.userInterestMapList[i].lat,
          state.userInterestMapList[i].lon,
        ];
        arr[i] = latlon;
      }
      return arr;
    },

    houseLatLng(state) {
      var arr = new Array(state.houseMapList.length);

      for (var i = 0; i < arr.length; ++i) {
        var latlon = [state.houseMapList[i].lat, state.houseMapList[i].lng];
        arr[i] = latlon;
      }
      return arr;
    },

    allTodosCount(state) {
      return state.todos.length;
    },
    completedTodosCount(state) {
      return state.todos.filter((todo) => {
        return todo.completed === true;
      }).length;
    },
    unCompletedTodosCount(state) {
      return state.todos.filter((todo) => {
        return todo.completed === false;
      }).length;
    },
  },
  mutations: {
    SET_CONDITIONS(state, conditions) {
      state.aptConditions = JSON.parse(JSON.stringify(conditions));
    },
    SET_CIRCLES(state, circles) {
      state.circles = JSON.parse(JSON.stringify(circles));
    },

    SET_AVG(state, avgs) {
      state.avgs = avgs;
    },

    SET_ARTICLENO(state, articleno) {
      state.articleno = articleno;
    },
    SET_QNANO(state, qnano) {
      state.qnano = qnano;
    },
    // 댓글 초기화
    SET_COMMENTS(state, comments) {
      state.comments = JSON.parse(JSON.stringify(comments));
    },
    /////////////////////////////// House start /////////////////////////////////////
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dong });
      });
    },

    SET_AROUND_STORE_LIST(state, aroundStores) {
      state.aroundStores = JSON.parse(JSON.stringify(aroundStores));
      state.mapList = JSON.parse(JSON.stringify(aroundStores));
    },

    SET_USER_INTEREST_LIST(state, userInterests) {
      state.userInterests = JSON.parse(JSON.stringify(userInterests));
      state.userInterestsMapList = JSON.parse(JSON.stringify(userInterests));
    },

    SET_HOUSE_LIST(state, houses) {
      console.log("SET_HOUSE_LIST 처음", houses);
      state.houses = [];
      // console.log("house", houses[0]);
      // console.log("apartmentName", houses[0]["apartmentName"]);
      // console.log("aroundStr", houses[0]["aroundStr"]);

      state.houses = JSON.parse(JSON.stringify(houses));
      state.houseMapList = JSON.parse(JSON.stringify(houses));
      console.log("SET_HOUSE_LIST 끝", state.houses);
    },

    CLEAR_AROUND_STORES_LIST(state) {
      state.aroundStores = [];
    },

    CLEAR_HOUSES_LIST(state) {
      state.houses = [];
    },

    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "시도 선택" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },

    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_DETAIL_AROUND_STORE(state, aroundStore) {
      state.aroundStore = aroundStore;
    },
    SET_DETAIL_USER_INTEREST(state, userInterest) {
      state.userInterest = userInterest;
    },
    /////////////////////////////// House end /////////////////////////////////////

    /////////////////////////////// Environ start /////////////////////////////////////
    SET_ENVIRON_LIST(state, environs) {
      state.environs = environs;
    },
    SET_DETAIL_ENVIRON(state, environ) {
      state.environ = environ;
    },
    /////////////////////////////// Environ end /////////////////////////////////////

    //////////////////////////// Todo List start //////////////////////////////////
    CREATE_TODO(state, todoItem) {
      state.todos.push(todoItem);
    },
    DELETE_TODO(state, todoItem) {
      const index = state.todos.indexOf(todoItem);
      state.todos.splice(index, 1);
    },
    UPDATE_TODO_STATUS(state, todoItem) {
      state.todos = state.todos.map((todo) => {
        if (todo === todoItem) {
          return {
            ...todo,
            completed: !todoItem.completed,
          };
        }
        return todo;
      });
    },
    //////////////////////////// Todo List end //////////////////////////////////
  },
  actions: {
    // 평균시세 (positionMode:sido,gugun,dong)
    getAvg({ commit }, positionMode) {
      http.get(`/avg/${positionMode}`).then(({ data }) => {
        commit("SET_AVG", data);
      });
    },
    // 코멘트
    getComments({ commit }, qnano) {
      http.get(`/comments/${qnano}`).then(({ data }) => {
        commit("SET_COMMENTS", data);
      });
    },
    /////////////////////////////// AroundStore start ///////////////////////////////
    getSidoAroundMap({ commit }) {
      http
        .get(`/apart/map/sido`)
        .then(({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugunAroundMap({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      http
        .get(`/apart/map/gugun`, { params })
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    /////////////////////////////// House start /////////////////////////////////////
    getSido({ commit }) {
      http
        .get(`/map/sido`)
        .then(({ data }) => {
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      http
        .get(`/map/gugun`, { params })
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log("error");
          console.log(error);
        });
    },

    getDong({ commit }, gugunCode) {
      const params = { gugun: gugunCode };
      http
        .get(`/map/dong`, { params })
        .then(({ data }) => {
          commit("SET_DONG_LIST", data);
        })
        .catch((error) => {
          console.log("error");
          console.log(error);
        });
    },

    getEnvironList({ commit }, gugunCode) {
      const params = { gugun: gugunCode };
      http
        .get(`/interestinfo/env/search`, { params })
        .then(({ data }) => {
          // console.log(commit, response);
          console.log(commit, data);
          commit("SET_ENVIRON_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getAroundStoreList({ commit }, datas) {
      //const params = { sido: sidoCode, sigugun: gugunCode, dong: dongCode };
      // const ctprvnCd = datas.sidoCode;
      const signguCd = datas.gugunCode;
      // const adongCd = datas.dongCode;
      // const SERVICE_KEY ="yOYPxjA2Luqpjh8gS0r0pw69WoBHUn5HXJzTznjhCK78Aab2ZiFJ5pAGNq%2FLoVzbI1pCfMG10RPiGyk%2BqfFAIQ%3D%3D"; //process.env.VUE_APP_APT_DEAL_API_KEY;
      const DECODE_SERVICE_KEY =
        "yOYPxjA2Luqpjh8gS0r0pw69WoBHUn5HXJzTznjhCK78Aab2ZiFJ5pAGNq/LoVzbI1pCfMG10RPiGyk+qfFAIQ==";

      const SERVICE_URL =
        "http://apis.data.go.kr/B553077/api/open/sdsc2/storeListInDong";
      const params = {
        serviceKey: DECODE_SERVICE_KEY, //decodeURIComponent(SERVICE_KEY),
        key: signguCd,
        divId: "signguCd", //ctprvnCd, signguCd, adongCd
        type: "json",
        indsLclsCd: "Q",
        // indsMclsCd: "Q12",
        // indsSclsCd: "Q12A01",
        pageNo: 1,
        numOfRows: 100,
      };
      http
        .get(SERVICE_URL, { params })
        .then(({ data }) => {
          commit("SET_AROUND_STORE_LIST", data.body.items);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getUserInterestList({ commit }, data) {
      const params = {
        id: data.id,
      };
      http
        .get(`/interest`, { params })
        .then(({ data }) => {
          console.log("commit, data");
          console.log(data);

          commit("SET_USER_INTEREST_LIST", data);
        })
        .catch((error) => {
          console.log("error");
          console.log(error);
        });
    },

    getHouseList({ commit }, data) {
      const params = {
        sidoCode: data.sidoCode,
        gugunCode: data.gugunCode,
        dongCode: data.dongCode,
      };
      http
        .get(`/map/apt2`, { params })
        .then(({ data }) => {
          console.log("3. result 출력");

          // 조건에 맞는 아파트들 간추리기
          //   return findApt(data, this.state.aptConditions);
          // })
          // .then((result) => {
          console.log("4. 아파트 리스트", data);
          console.log("4. 아파트 리스트 aroundStr", data[0]["aroundStr"]);

          commit("SET_HOUSE_LIST", data);

          //console.log("commit, data");
          //commit("CLEAR_AROUND_STORES_LIST");
          //commit("SET_HOUSE_LIST", data);
        })
        .catch((error) => {
          console.log("error");
          console.log(error);
        });
    },
    detailHouse({ commit }, house) {
      // 나중에 house.일련번호를 이용하여 API 호출
      // console.log(commit, house);
      commit("SET_DETAIL_HOUSE", house);
    },

    detailUserInterest({ commit }, userInterest) {
      // 나중에 house.일련번호를 이용하여 API 호출
      // console.log(commit, house);
      commit("SET_DETAIL_USER_INTEREST", userInterest);
    },

    /////////////////////////////// House end /////////////////////////////////////

    ////////////////////////////
    detailAroundStore({ commit }, house) {
      // 나중에 house.일련번호를 이용하여 API 호출
      // console.log(commit, house);
      commit("SET_DETAIL_AROUND_STORE", house);
    },

    /////////////////////////////// Environ end /////////////////////////////////////

    detailEnviron({ commit }, house) {
      commit("SET_DETAIL_ENVIRON", house);
    },
    /////////////////////////////// Environ end /////////////////////////////////////

    //////////////////////////// Todo List start //////////////////////////////////

    // destructuring 활용
    createTodo({ commit }, todoItem) {
      commit("CREATE_TODO", todoItem);
    },
    deleteTodo({ commit }, todoItem) {
      commit("DELETE_TODO", todoItem);
    },
    updateTodoStatus({ commit }, todoItem) {
      commit("UPDATE_TODO_STATUS", todoItem);
    },
    //////////////////////////// Todo List end //////////////////////////////////
  },
});
