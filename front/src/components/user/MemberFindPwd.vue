<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>비밀번호 찾기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="!isOk"
              >일치하는 정보가 없습니다.</b-alert
            >
            <b-form-group label="이메일" label-for="userEmail">
              <b-form-input
                id="userEmail"
                type="email"
                v-model="userInfoFind.userEmail"
                required
                placeholder="이메일을 입력해 주세요."
                @keyup.enter="findPwd"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="연락처" label-for="phoneNum">
              <b-form-input
                id="phoneNum"
                type="tel"
                v-model="userInfoFind.phoneNum"
                required
                placeholder="연락처를 입력해 주세요."
                @keyup.enter="findPwd"
              ></b-form-input>
            </b-form-group>

            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="findPwd"
              >비밀번호 찾기</b-button
            >

            <b-modal id="pwd-modal">{{ msg }}</b-modal>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";

export default {
  name: "MemberLogin",
  components: {},
  data() {
    return {
      msg: null,
      isOk: true,
      userInfoFind: {
        phoneNum: null,
        userEmail: null,
      },
    };
  },
  //   computed: {
  // ...mapState(memberStore, ["isLogin", "isLoginError"]),
  //   },
  methods: {
    findPwd() {
      http.post(`/user/findpwd`, this.userInfoFind).then(({ data }) => {
        console.log(data);
        if (data.msg) {
          this.msg = data.msg;
          this.$bvModal.show("pwd-modal");
          this.isOk = true;
        } else {
          this.isOk = false;
        }
        // alert(data.status);
      });
    },
  },
};
</script>

<style></style>
