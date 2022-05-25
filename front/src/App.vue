<template>
  <div id="app">
    <nav-bar />
    <router-view />
    <footer-bar />
  </div>
</template>

<script>
import NavBar from "@/components/layout/NavBarB.vue";
import FooterBar from "@/components/layout/FooterBar.vue";
export default {
  name: "App",
  components: {
    NavBar,
    FooterBar,
  },
};
</script>

<style>
#app > .container {
  position: relative;
  top: 100px;
  margin-bottom: 300px;
}
a {
  text-decoration: none;
}
a:hover {
  text-decoration: none;
  font-weight: bold;
  color: chartreuse;
}
a.router-link-exact-active {
  color: #3aa4ca;
}
</style>
