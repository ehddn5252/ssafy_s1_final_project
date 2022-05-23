<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>비밀번호 변경</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="비밀번호 확인:" label-for="userId">
              <b-form-input
                id="preUserPwd"
                type="password"
                v-model="pwd.preUserPwd"
                required
                placeholder="이전 비밀번호"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="변경할 비밀번호:" label-for="userPwd">
              <b-form-input
                type="password"
                id="userPwd"
                v-model="pwd.userPwd"
                required
                placeholder="새로운 비밀번호"
                @keyup.enter="modifyPwd"
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="modifyPwd"
              >비밀번호 변경</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
import http from "@/api/http";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  components: {},
  data() {
    return {
      pwd: {
        preUserPwd: null,
        userPwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  //   computed: {
  // ...mapState(memberStore, ["isLogin", "isLoginError"]),
  //   },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    modifyPwd() {
      http
        .put(`/user/modify`, { userId: this.userInfo.userId, ...this.pwd })
        .then(({ data }) => {
          console.log(data);
          alert(data.status);
          if (data.statusCode == 1) {
            this.$router.push({ name: "home" });
          }
        });
    },
  },
};
</script>

<style></style>
