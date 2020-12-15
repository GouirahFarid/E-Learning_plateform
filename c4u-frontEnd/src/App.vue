<template>
  <div id="app">
    <NavBar></NavBar>
    <div class="container-fluid m-0 p-0">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
  import NavBar from "./components/NavBar"
export default {
  name: "App",
  components: {NavBar},

  created(){
    this.$store.dispatch("auth/getPerson");
    this.$store.dispatch("formation/getCategories");
  },
};
</script>
