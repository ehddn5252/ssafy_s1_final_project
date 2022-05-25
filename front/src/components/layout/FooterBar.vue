<template>
  <footer id="footer">
    <div class="footer-top">
      <div class="container">
        <div class="row">
          <div class="col-lg-4 col-md-6">
            <div class="footer-info">
              <h3>HappyHouse</h3>
              <p class="pb-3">
                <em>최고의 서비스</em>
              </p>
              <p>
                싸피광역시 싸피동 11-11, 대한민국<br /><br />
                <strong>Phone:</strong> 5959-5959<br />
                <strong>Email:</strong> contact@happyhouse.co.kr<br />
              </p>
              <div class="social-links mt-3">
                <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
                <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
                <a href="#" class="instagram"
                  ><i class="bx bxl-instagram"></i
                ></a>
                <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
                <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
              </div>
            </div>
          </div>

          <div class="col-lg-3 mx-4 col-md-6 footer-links">
            <h4>Useful Links</h4>
            <ul class="divided-list list-unstyled text-center">
              <li><i class="bx bx-chevron-right"></i> <a href="/">Home</a></li>
              <li>
                <i class="bx bx-chevron-right"></i>
                <a href="/notice">공지사항</a>
              </li>
              <li>
                <i class="bx bx-chevron-right"></i>
                <a href="/sitemap">사이트맵</a>
              </li>
            </ul>
          </div>

          <div class="col-lg-3 col-md-6 footer-links">
            <h4>서비스들</h4>
            <ul class="divided-list list-unstyled text-center">
              <li>
                <i class="bx bx-chevron-right"></i>
                <a href="/apart">주택 실거래가 조회</a>
              </li>
              <li>
                <i class="bx bx-chevron-right"></i>
                <a href="/interest/register">관심 지역 등록</a>
              </li>
              <li>
                <i class="bx bx-chevron-right"></i>
                <a href="/interestinfo/store">주변탐방 업종 정보 조회</a>
              </li>
              <li>
                <i class="bx bx-chevron-right"></i>
                <a href="/interestinfo/env">주변환경 정보 조회</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="copyright">
        &copy; Copyright <strong><span>Squadfree</span></strong
        >. All Rights Reserved
      </div>
      <div class="credits">
        <!-- All the links in the footer should remain intact. -->
        <!-- You can delete the links only if you purchased the pro version. -->
        <!-- Licensing information: https://bootstrapmade.com/license/ -->
        <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/squadfree-free-bootstrap-template-creative/ -->
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>
  </footer>
</template>

<script>
import { mapState, mapMutations } from "vuex";
const memberStore = "memberStore";
export default {
  name: "HeaderNaviBar",
  data() {
    return {
      view: {
        topOfPage: true,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  beforeMount() {
    window.addEventListener("scroll", this.handleScroll);
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
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
  },
};
</script>

<style scoped>
#footer {
  background: #263f49;
  padding: 0 0 30px 0;
  color: #fff;
  font-size: 14px;
}
#footer .footer-top {
  padding: 60px 0 30px 0;
}
#footer .footer-top .footer-info {
  margin-top: -90px;
  margin-bottom: 15px;
  background: white;
  color: #2f4d5a;
  border-top: 4px solid #67b0d1;
  text-align: center;
  padding: 30px 20px;
}
#footer .footer-top .footer-info h3 {
  font-size: 24px;
  margin: 0 0 20px 0;
  padding: 2px 0 2px 0;
  line-height: 1;
  font-weight: 700;
}
#footer .footer-top .footer-info p {
  font-size: 14px;
  line-height: 24px;
  margin-bottom: 0;
  font-family: "Raleway", sans-serif;
}
#footer .footer-top .social-links a {
  font-size: 18px;
  display: inline-block;
  background: #2f4d5a;
  color: #fff;
  line-height: 1;
  padding: 8px 0;
  margin-right: 4px;
  border-radius: 50%;
  text-align: center;
  width: 36px;
  height: 36px;
  transition: 0.3s;
}
#footer .footer-top .social-links a:hover {
  background: #67b0d1;
  color: #fff;
  text-decoration: none;
}
#footer .footer-top h4 {
  font-size: 16px;
  font-weight: 600;
  color: #fff;
  position: relative;
  padding-bottom: 12px;
}
#footer .footer-top .footer-links {
  margin-bottom: 30px;
}
#footer .footer-top .footer-links ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
#footer .footer-top .footer-links ul i {
  padding-right: 2px;
  color: #b5d9e9;
  font-size: 18px;
  line-height: 1;
}
#footer .footer-top .footer-links ul li {
  padding: 10px 0;
  display: flex;
  align-items: center;
}
#footer .footer-top .footer-links ul li:first-child {
  padding-top: 0;
}
#footer .footer-top .footer-links ul a {
  color: #fff;
  transition: 0.3s;
  display: inline-block;
  line-height: 1;
}
#footer .footer-top .footer-links ul a:hover {
  color: #a2cfe3;
}
#footer .footer-top .footer-newsletter form {
  margin-top: 30px;
  background: #fff;
  padding: 6px 10px;
  position: relative;
  border-radius: 4;
}
#footer .footer-top .footer-newsletter form input[type="email"] {
  border: 0;
  padding: 4px;
  width: calc(100% - 110px);
}
#footer .footer-top .footer-newsletter form input[type="submit"] {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  border: 0;
  background: none;
  font-size: 16px;
  padding: 0 20px;
  background: #67b0d1;
  color: #fff;
  transition: 0.3s;
  border-radius: 4;
}
#footer .footer-top .footer-newsletter form input[type="submit"]:hover {
  background: #409cc5;
}
#footer .copyright {
  border-top: 1px solid #385b6b;
  text-align: center;
  padding-top: 30px;
}
#footer .credits {
  padding-top: 10px;
  text-align: center;
  font-size: 13px;
  color: #fff;
}
@media (max-width: 575px) {
  #footer .footer-top .footer-info {
    margin: -20px 0 30px 0;
  }
}
</style>
