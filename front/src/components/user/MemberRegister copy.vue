<template>
  <div class="registercontainer">
    <v-text-field
      class="pl-3 pr-3"
      :rules="emailRules"
      required
      v-model="userInfo.userName"
    ></v-text-field>
  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: "MemberRegister",

  data() {
    return {
      userInfo: {
        userId: "",
        userName: "",
        phoneNum: "",
        userEmail: "",
        userPwd: "",
        userGender: "",
        userBirth: "",
      },
      type: "",
      idcheck: false,
    };
  },

  computed: {
    validation() {
      return (
        this.userInfo.userId.length > 4 && this.userInfo.userId.length < 13
      );
    },
  },

  methods: {
    onSubmit(event) {
      console.log("int onSubmit");
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.userInfo.userName &&
        ((msg = "이름 입력해주세요"),
        (err = false),
        this.$refs.userName.focus());
      err &&
        !this.userInfo.phoneNum &&
        ((msg = "휴대폰 번호를 입력해주세요"),
        (err = false),
        this.$refs.phoneNum.focus());
      err &&
        !this.userInfo.userEmail &&
        ((msg = "이메일 입력해주세요"),
        (err = false),
        this.$refs.userEmail.focus());
      err &&
        !this.userInfo.userPwd &&
        ((msg = "패스워드를 입력해주세요"),
        (err = false),
        this.$refs.userPwd.focus());
      err &&
        !this.userInfo.userGender &&
        ((msg = "성별을 입력해주세요"),
        (err = false),
        this.$refs.userGender.focus());
      err &&
        !this.userInfo.userBirth &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.userBirth.focus());
      err &&
        !this.userInfo.userId &&
        ((msg = "아이디를 입력해주세요"),
        (err = false),
        this.$refs.userId.focus());

      if (!err) alert(msg);
      else {
        console.log("this.registerUserInfo() is called");
        this.registUserInfo();
      }
    },

    onReset(event) {
      event.preventDefault();
      this.userInfo.userId = "";
      this.userInfo.userName = "";
      this.userInfo.phoneNum = "";
      this.userInfo.userEmail = "";
      this.userInfo.userPwd = "";
      this.userInfo.userGender = "";
      this.userInfo.userBirth = "";
      // this.$router.push({ name: "signUp" });
    },

    registUserInfo() {
      console.log("in registerUserInfo");
      http
        .post(`/user/register`, this.userInfo)
        .then(({ data }) => {
          let msg = "아이디가 중복됩니다.";
          if (data === 1) {
            msg = "등록이 완료되었습니다.";
            this.$router.push({ name: "home" });
          }
          alert(msg);
        })
        .catch((error) => {
          let msg = "아이디가 중복됩니다.";
          console.log(error);
          alert(msg);
        });
    },
    moveList() {
      this.$router.push({ name: "signUp" });
    },
    idCheck() {
      // 중복되면 idcheck true
      console.log(this.userInfo.userId);
      console.log(this.idcheck);
      http
        .get(`/user/idcheck?ckid=${this.userInfo.userId}`)
        .then(({ data }) => {
          if (data.idcount == 1) {
            this.idcheck = true;
          } else {
            this.idcheck = false;
          }
        });
    },
  },
};
</script>

<style scoped>
/* @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap"); */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: "Poppins", sans-serif;
}
.btn_area {
  margin: 30px 0 5px;
}
.registercontainer {
  margin: auto;
  max-width: 700px;
  width: 100%;
  background-color: #fff;
  padding: 25px 30px;
  border-radius: 5px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.15);
}
.registercontainer .title {
  font-size: 25px;
  font-weight: 500;
  position: relative;
}
.registercontainer .title::before {
  content: "";
  position: absolute;
  left: 0;
  bottom: 0;
  height: 3px;
  width: 30px;
  border-radius: 5px;
  background: linear-gradient(135deg, #71b7e6, #9b59b6);
}
.content form .user-details {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin: 20px 0 12px 0;
}
form .user-details .input-box {
  margin-bottom: 15px;
  width: calc(100% / 2 - 20px);
}
form .input-box span.details {
  display: block;
  font-weight: 500;
  margin-bottom: 5px;
}
.user-details .input-box input {
  height: 45px;
  width: 100%;
  outline: none;
  font-size: 16px;
  border-radius: 5px;
  padding-left: 15px;
  border: 1px solid #ccc;
  border-bottom-width: 2px;
  transition: all 0.3s ease;
}
.user-details .input-box input:focus,
.user-details .input-box input:valid {
  border-color: #9b59b6;
}
form .gender-details .gender-title {
  font-size: 20px;
  font-weight: 500;
}
form .category {
  display: flex;
  width: 80%;
  margin: 14px 0;
  justify-content: space-between;
}
form .category label {
  display: flex;
  align-items: center;
  cursor: pointer;
}
form .category label .dot {
  height: 18px;
  width: 18px;
  border-radius: 50%;
  margin-right: 10px;
  background: #d9d9d9;
  border: 5px solid transparent;
  transition: all 0.3s ease;
}
#dot-1:checked ~ .category label .one,
#dot-2:checked ~ .category label .two,
#dot-3:checked ~ .category label .three {
  background: #9b59b6;
  border-color: #d9d9d9;
}
form input[type="radio"] {
  display: none;
}
form .button {
  height: 45px;
  margin: 35px 0;
}
form .button input {
  height: 100%;
  width: 100%;
  border-radius: 5px;
  border: none;
  color: #fff;
  font-size: 18px;
  font-weight: 500;
  letter-spacing: 1px;
  cursor: pointer;
  transition: all 0.3s ease;
  background: #1894e7;
}
form .button input:hover {
  /* transform: scale(0.99); */
  background: #5b85a4;
}
form .button input:disabled {
  cursor: default;
  background: #c0c0c0;
}
@media (max-width: 584px) {
  .container {
    max-width: 100%;
  }
  form .user-details .input-box {
    margin-bottom: 15px;
    width: 100%;
  }
  form .category {
    width: 100%;
  }
  .content form .user-details {
    max-height: 300px;
    overflow-y: scroll;
  }
  .user-details::-webkit-scrollbar {
    width: 5px;
  }
}
@media (max-width: 459px) {
  .container .content .category {
    flex-direction: column;
  }
}

.findPwd-btn {
  display: block;
  width: 100%;
  height: 40px;
  color: #f1f1f1;
  background: rgb(0, 152, 212);
  background-size: 150%;
  outline: none;
  cursor: pointer;
  transition: 0.5s;
  border: none;
  border-radius: 5px;
}

.findPwd-btn:hover,
.findPwd-btn:active,
.findPwd-btn:focus {
  background: #50a3a2;
}

.findpwd-form {
  position: relative;
  z-index: 1;
  background: #ffffff;
  max-width: 800px;
  margin: 0 auto 100px;
  padding: 45px;
  text-align: center;
  box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0 rgba(0, 0, 0, 0.24);
}
section {
  padding: 40px 0 100px;
}
</style>
