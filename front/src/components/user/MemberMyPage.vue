<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page</template>

          <template #lead> 내 정보 확인페이지입니다. </template>

          <hr class="my-4" />

          <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end"
                ><label for="input-live">이름</label></b-col
              >
              <b-col cols="4" align-self="start">
                <b-form-input
                  id="input-live"
                  v-model="userInfo.userName"
                  :state="nameState"
                  aria-describedby="input-live-help input-live-feedback"
                  placeholder="Enter your Email"
                  trim
                  v-show="isModify"
                ></b-form-input>
                <b-form-invalid-feedback id="input-live-feedback">
                  Enter your name
                </b-form-invalid-feedback>
                <span v-show="!isModify">{{ userInfo.userName }}</span>
              </b-col>
              <b-col cols="2"></b-col>
            </b-row>

            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end"
                ><label for="input-live">이메일</label></b-col
              >
              <b-col cols="4" align-self="start">
                <b-form-input
                  id="input-live"
                  v-model="userInfo.userEmail"
                  :state="emailState"
                  aria-describedby="input-live-help input-live-feedback"
                  placeholder="Enter your Email"
                  trim
                  type="email"
                  v-show="isModify"
                ></b-form-input>
                <b-form-invalid-feedback id="input-live-feedback">
                  Enter at least 3 letters
                </b-form-invalid-feedback>
                <span v-show="!isModify">{{ userInfo.userEmail }}</span>
              </b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">성별</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.userGender
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">생년월일</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.userBirth
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end"
                ><label for="input-live">연락처</label></b-col
              >
              <b-col cols="4" align-self="start">
                <b-form-input
                  id="input-live"
                  v-model="userInfo.phoneNum"
                  :state="phoneState"
                  aria-describedby="input-live-help input-live-feedback"
                  placeholder="Enter your Email"
                  type="tel"
                  trim
                  v-show="isModify"
                ></b-form-input>
                <b-form-invalid-feedback id="input-live-feedback">
                  Enter your phone Number
                </b-form-invalid-feedback>
                <span v-show="!isModify">{{ userInfo.phoneNum }}</span>
              </b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입일</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.registDate
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button
            variant="primary"
            href="#"
            class="mr-1"
            @click="modifyUserInfo"
            >정보수정</b-button
          >
          <b-button variant="danger" href="#" @click="deleteUserInfo"
            >회원탈퇴</b-button
          >
        </b-jumbotron>
        <b-button
          type="button"
          variant="success"
          class="m-1"
          @click="moveModifyPwd"
          >비밀번호 변경</b-button
        >
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import http from "@/api/http";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  components: {},
  data() {
    return {
      isModify: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    nameState() {
      return this.userInfo.userName.length > 0 ? true : false;
    },
    emailState() {
      return this.userInfo.userEmail.length > 0 ? true : false;
    },
    phoneState() {
      return this.userInfo.phoneNum.length > 0 ? true : false;
    },
  },
  methods: {
    ...mapActions(memberStore, ["getUserInfo"]),
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    logout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
    modifyUserInfo() {
      if (this.isModify) {
        http.put(`/user/modify`, this.userInfo).then(({ data }) => {
          alert(data.status);
        });

        this.isModify = false;
      } else {
        this.isModify = true;
      }
    },
    deleteUserInfo() {
      http
        .delete(`/user/delete/${this.userInfo.userId}`)
        .then(({ data }) => {
          console.log(data);
          this.logout();
          this.comments = data;
          alert(data.status);
        })
        .then(() => {
          this.$router.push({ name: "home" });
        });
    },
    async setUserInfo() {
      let token = sessionStorage.getItem("access-token");
      await this.getUserInfo(token);
    },
    moveModifyPwd() {
      this.$router.push({ name: "modifypwd" });
    },
  },
};
</script>

<style></style>
