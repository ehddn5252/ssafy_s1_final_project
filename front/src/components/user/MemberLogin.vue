<template>
  <v-container
    style="position: relative; top: 20%; margin-left: 20%"
    class="text-xs-center"
  >
    <v-layout row wrap class="text-xs-center">
      <v-flex>
        <v-card flat class="mx-auto" max-width="800">
          <v-row style="margin-top: 60px">
            <v-col>
              <v-form style="width: 400px; height: 300px">
                <b-alert show variant="danger" v-if="isLoginError"
                  >아이디 또는 비밀번호를 확인하세요.</b-alert
                >
                <div class="mx-3">
                  <v-icon color="black" size="30px">mdi-account</v-icon>
                  userId
                  <div class="mx-1">
                    <v-text-field
                      id="userId"
                      autocomplete="off"
                      placeholder="userId"
                      v-model="user.userId"
                      @keyup.enter="confirm"
                      required
                      autocomp
                    ></v-text-field>
                  </div>
                </div>
                <div class="mx-3">
                  <v-icon color="black" size="30px">mdi-lock</v-icon>
                  userPassword
                  <div class="mx-1">
                    <v-text-field
                      placeholder="userPassword"
                      id="userPwd"
                      type="password"
                      v-model="user.userPwd"
                      @keyup.enter="confirm"
                      required
                    ></v-text-field>
                  </div>
                </div>
                <v-container class="text-left">
                  <div>
                    처음 방문하신거라면?
                    <a class="anchor" @click="movePage">회원가입</a>
                  </div>
                  <div class="mt-3">
                    비밀번호가 기억나지 않으시다면?
                    <a class="anchor" @click="moveFindPwd">비밀번호 찾기</a>
                  </div>
                </v-container>

                <v-card-actions>
                  <v-btn color="#2c4f91" dark large block @click="confirm"
                    >로그인</v-btn
                  >
                </v-card-actions>
              </v-form>
            </v-col>
          </v-row>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
// import KakaoLogin from "@/components/user/KakaoLogin.vue";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  components: {
    // KakaoLogin,
  },
  data() {
    return {
      user: {
        userId: null,
        userPwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  destroyed() {
    this.SET_IS_LOGIN_ERROR(false);
  },

  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN_ERROR"]),
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "signUp" });
    },
    moveFindPwd() {
      this.$router.push({ name: "findpwd" });
    },
  },
};
</script>

<style>
.anchor {
  color: rgb(84, 90, 177);
  cursor: pointer;
}
</style>
