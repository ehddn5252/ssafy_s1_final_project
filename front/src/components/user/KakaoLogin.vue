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
                  <v-icon color="black" size="30px">person</v-icon>
                  userId
                  <div class="mx-1">
                    <v-text-field
                      id="userId"
                      placeholder="userId"
                      v-model="user.userId"
                      @keyup.enter="confirm"
                      required
                    ></v-text-field>
                  </div>
                </div>
                <div class="mx-3">
                  <v-icon color="black" size="30px">lock</v-icon>
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

                <v-card-actions>
                  <v-btn color="#2c4f91" dark large block @click="confirm"
                    >로그인</v-btn
                  >
                  <span>
                    처음 방문하신거라면?
                    <a @click="movePage">회원가입</a>
                  </span>
                  <span>
                    비밀번호가 기억나지 않으시다면?
                    <a @click="moveFindPwd">비밀번호 찾기</a>
                  </span>
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
export default {
  data() {
    return {
      userId: null,
      userPassword: null,
    };
  },
  methods: {
    loginSubmit() {
      let saveData = {};
      saveData.userId = this.userId;
      saveData.userPassword = this.userPassword;

      try {
        this.$axios
          .post(HOST + "/signin", JSON.stringify(saveData), {
            headers: {
              "Content-Type": `application/json`,
            },
          })
          .then((res) => {
            if (res.status === 200) {
              // 로그인 성공시 처리해줘야할 부분
            }
          });
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>
출처: https://developerjournal.tistory.com/15 [개발 일기:티스토리]
