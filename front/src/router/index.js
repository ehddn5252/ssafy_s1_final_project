import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import store from "@/store/index.js";
// import { Store } from "vuex";

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "signIn" });
    // router.push({ name: "signIn" });
    return Promise.reject();
  } else {
    // console.log("로그인 했다.");
    next();
    // return Promise.resolve();
  }
};
const onlyVip = (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  if (!checkUserInfo) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "signIn" });
  } else {
    if (checkUserInfo.manager != "vip") {
      alert("VIP 멤버십 가입 후 이용 가능합니다.");
      next({ name: "home" });
      // router.push({ name: "signIn" });
    } else {
      // console.log("로그인 했다.");
      next();
    }
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },

  {
    path: "/chart",
    name: "chart",
    redirect: "chart/list",
    beforeEnter: onlyVip,
    component: () => import("@/views/ChartView.vue"),
    children: [
      {
        path: "list",
        name: "listChart",
      },
    ],
  },
  {
    path: "/map",
    name: "map",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/MapView.vue"),
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/MemberView.vue"),
    children: [
      {
        path: "modifypwd",
        name: "modifypwd",
        component: () => import("@/components/user/MemberModifyPwd.vue"),
      },
      {
        path: "findpwd",
        name: "findpwd",
        component: () => import("@/components/user/MemberFindPwd.vue"),
      },
      {
        path: "singin",
        name: "signIn",
        component: () => import("@/components/user/MemberLogin.vue"),
      },
      {
        path: "singup",
        name: "signUp",
        component: () => import("@/components/user/MemberRegister.vue"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/MemberMyPage.vue"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/BoardView.vue"),
    redirect: "/board/list",
    beforeEnter: onlyAuthUser,
    children: [
      {
        path: "list",
        name: "boardList",
        component: () => import("@/components/board/BoardList.vue"),
      },
      {
        path: "write",
        name: "boardRegister",
        component: () => import("@/components/board/BoardRegister.vue"),
      },
      {
        path: "detail/:articleno",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail.vue"),
      },
      {
        path: "modify/:articleno",
        name: "boardModify",
        component: () => import("@/components/board/BoardModify.vue"),
      },
      {
        path: "delete/:articleno",
        name: "boardDelete",
        component: () => import("@/components/board/BoardDelete.vue"),
      },
    ],
  },

  {
    path: "/interestregion",
    name: "interestregion",
    redirect: "/interestregion/list",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/InterestRegionView.vue"),
    children: [
      {
        path: "list",
        name: "listInterestregion",
        component: () =>
          import("@/components/interestregion/InterestRegionList.vue"),
      },
    ],
  },

  {
    path: "/interestinfo",
    name: "interestinfo",
    component: () => import("@/views/AroundStoreView.vue"),
    redirect: "/interestinfo/store",
    beforeEnter: onlyAuthUser,
    children: [
      {
        path: "store",
        name: "store",
        // component: () => import("@/components/around/AroundStoreSearchBar.vue"),
      },
    ],
  },
  {
    path: "/qna",
    name: "qna",
    component: () => import("@/views/QnaView.vue"),
    beforeEnter: onlyAuthUser,
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "qnaList",
        component: () => import("@/components/qna/QnaList.vue"),
      },
      {
        path: "write",
        name: "qnaRegister",
        component: () => import("@/components/qna/QnaRegister.vue"),
      },
      {
        path: "detail/:qnano",
        name: "qnaDetail",
        component: () => import("@/components/qna/QnaDetail.vue"),
      },
      {
        path: "modify/:qnano",
        name: "qnaModify",
        component: () => import("@/components/qna/QnaModify.vue"),
      },
      {
        path: "delete/:qnano",
        name: "qnaDelete",
        component: () => import("@/components/qna/QnaDelete.vue"),
      },
    ],
  },
  // {
  //   path: "/instagram",
  //   name: "instagram",
  //   component: () => import("@/views/InstagramView.vue"),
  // },
  {
    path: "/house",
    name: "house",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/HouseView.vue"),
  },
  // {
  //   path: "/environ",
  //   name: "environ",
  //   beforeEnter: onlyAuthUser,
  //   component: () => import("@/views/EnvironView.vue"),
  // },
  // {
  //   path: "/todo",
  //   name: "todo",
  //   beforeEnter: onlyAuthUser,
  //   component: () => import("@/views/TodoView.vue"),
  // },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
