<template>
  <div class="login p-3 my-2">
    <div class="row">
      <div class="col-12 logo text-center">
          <img class="img img-fluid" src="../assets/images/C4U.png" alt="not found">
      </div>
        <div class="col-12 text-center">
            <h4>Welcome Back!</h4>
        </div>
        <div class="col-12 row align-items-center justify-content-center">
            <form @submit.prevent="handleLogin()">
               <div class="row">
                   <div v-if="message" class="col-12 alert alert-danger" role="alert">{{ message}}</div>
                   <div class="col-12 my-2">
                       <div class="form-group">
                           <label class="h5" for="username">Username</label>
                           <input v-model="user.username" v-validate="'required'" type="text" class="form-control p-4" id="username"  placeholder="Username" required>
                       </div>
                   </div>
                   <div class="col-12 my-2">
                       <div class="form-group">
                           <label class="h5" for="password">Password</label>
                           <input v-model="user.password" type="password" class="form-control p-4" id="password" placeholder="password" required="required" >
                       </div>
                   </div>
                   <div class="col-12 my-2">
                       <div class="form-group">
                           <button class="btn btn-block p-3">LOGIN</button>
                       </div>
                   </div>
                   <div class="col-12 text-center my-2">
                       <h3>I don't have an account? <router-link :to="{name: 'Login',params: {login:false}}"  tag="a">Sign up</router-link> </h3>
                   </div>
               </div>
            </form>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginForm",
    data(){
      return{
          user:{
              username:'',
              password:''
          },
          message:''
      }
    },
    methods: {
        handleLogin() {
            this.$validator.validateAll().then(isValid => {
                if (!isValid) {
                    return;
                }
                if (this.user.username && this.user.password) {
                    this.$store.dispatch("auth/login", this.user).then(() => {this.$router.push("/");},
                        error => {
                            this.message = (error.response && error.response.data) || error.message || error.toString();
                        }
                    );
                }
            });
        }
    }
};
</script>

<style scoped>
    .login{
        background-color: #edffff;
        border-radius: 2%;
        width: 90%;
    }
    .img-fluid{
        height: 200px;
        width: 200px;
    }
    form{
        width: 90%;
    }
    .form-control{
        background-color: #dbf4f6;
    }
    .btn-block{
        background-color: #efb932;
        font-weight: bold;
    }
</style>
