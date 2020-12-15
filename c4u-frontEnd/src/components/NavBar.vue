<template>
  <div class="container-fluid">
    <nav class="navbar navbar-expand-lg navbar-light fixed-top">
      <a class="navbar-brand" href="#"
        ><router-link to="/"><img src="../assets/images/C4U.png" width="100" height="70" alt="" /></router-link>
      </a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-5">
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle mx-2 h5 mt-1" style="color:#ffad10 "
              href="#"
              id="navbarDropdown"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false">
              Categories</a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown" style="margin-top: 10px;width: 350px;border-radius:6%">
              <router-link
                v-for="(category,index) in $store.state.formation.categories"
                :key="category.id"
                tag="a"
                class="dropdown-item px-3 py-2 rounded-pill"
                :to='{name:"CategoryCourses",params:{id:index}}'
                >{{ category.title }}</router-link>
            </div>
          </li>
          <li class="nav-item">
            <form class="form-inline">
              <input class="form-control h-75 mx-2 py-2 input-search"
                     style="width: 500px;background-color: #98eee6;border: 1px solid #ffad10;"
                type="search" placeholder="Search" aria-label="Search"/>
                <i  class="mdi mdi-magnify mdi-36px" style="color: #ffad10"></i>
            </form>
          </li>
        </ul>
      </div>
      <div class="dropdown dropleft" v-if="$store.state.auth.userState.loggedIn">
          <i class="mdi mdi-account-circle mr-5 mdi-48px " data-toggle="dropdown"></i>
        <div class="dropdown-menu" style="margin-top: 70px;width: 300px;border-radius: 3%">
          <router-link :to="{name:'Profile',params:{type:$store.state.auth.userState.user.roles[0].roleName,account:'account'}}" class="dropdown-item rounded-pill h3"> <i class="mdi mdi-account mx-3"></i>{{$store.state.auth.userState.user.username}}</router-link>
          <router-link :to="{name:'Profile',params:{type:$store.state.auth.userState.user.roles[0].roleName,account:'myCourses'}}" class="dropdown-item rounded-pill h3"> <i class="mdi mdi-view-dashboard mx-3"></i>Dashboard</router-link>
          <p @click="logout" class="dropdown-item rounded-pill h3"><i class="mdi mdi-logout mx-3"></i>Logout</p>
        </div>
      </div>
      <router-link v-if="!$store.state.auth.userState.loggedIn"  class="signup  py-2 px-3 mx-3" tag="button" :to="{name: 'Login',params: {login:false}}"><i class="mdi mdi-login-variant"></i>sign up</router-link>
      <router-link v-if="!$store.state.auth.userState.loggedIn"  class="login py-2 px-3 mx-3 border-0" tag="button" :to="{name: 'Login',params: {login:true}}"><i class="mdi mdi-login"></i>sign in</router-link>
    </nav>
  </div>
</template>

<script>
export default {
  name: "NavBar",
  data(){
    return{
      isLogin:null,
    }
  },
  methods:{
    logout(){
      this.$store.dispatch('auth/logout').then(() => {this.$router.push("/");});
    },
    goHome(){
      this.$router.push("/");
    }
  },
  created() {
    this.$store.dispatch("auth/getPerson")
  }
};
</script>

<style scoped>
.navbar {
  background: #1a2980;
  background: linear-gradient(to right, #1a2980, #26d0ce);
  height: 60px;
  box-shadow: 0 0 0 5px #efb932;
}
  .dropdown-item:hover{
    background-color:#ffc128 ;
  }
.signup{
 border: 2px solid #ffc128;
  background:none;
  color: #ffc128;
  font-weight: bold;
}
.login{
  background-color:#ffc128;
  color: #050505;
  font-weight: bold;
}

</style>
