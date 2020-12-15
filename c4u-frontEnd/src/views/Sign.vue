<template>
  <div class="container-fluid p-0 mx-0 mt-5">
    <div class="row">
      <div class="col-4 sign-left p-0 mx-0">
        <div class="lef-img"></div>
      </div>
      <div class="col-8 sign-right row justify-content-center  align-items-center p-4">
        <LoginForm v-if="login"></LoginForm>
        <SignUpForm v-else></SignUpForm>
      </div>
    </div>
  </div>
</template>

<script>
  import LoginForm from "../components/LoginForm"
  import SignUpForm from "../components/SignUpForm";
export default {
  name: "Sign",
  components:{SignUpForm, LoginForm},
  data(){
    return{
      login:Boolean(this.$route.params.login),
    }
  },
  watch:{
    '$route.params.login':function (login) {
      this.login=login
    },

  },
  mounted(){
    if (this.$store.state.auth.userState.loggedIn) {
      this.$router.push("/");
    }
  }
};
</script>

<style scoped>
.sign-left {
  background-image: url("../assets/online.jpg");
  background-size: 100% 100%;
    height: auto;
}
.lef-img {
  background-color: #2b2b4d;
  width: 100%;
    height: 100%;
  opacity: 0.7;
}
  .sign-right{
    background-color: #ecedf6;
  }
</style>
