<template>
  <div class="login my-2">
    <div class="row">
      <div class="col-12 text-center my-2">
        <h4>Create Your Free Account</h4>
      </div>
      <div class="col-12 row align-items-center justify-content-center">
        <form action="" method="post" @submit="formValidation">
          <div class="row">
            <div class="col-12 alert alert-danger" v-if="formErrors.length>0">
              <ul>
                <li v-for="error in formErrors" :key="error">{{error}}</li>
              </ul>
            </div>
            <div class="col-12 my-1">
              <div class="row">
                <div class="col-6">
                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      id="professor"
                      value="professor"
                      v-model="user.accountType"
                    />
                    <label class="form-check-label" for="professor"
                      >Professor</label
                    >
                  </div>
                </div>
                <div class="col-6">
                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      id="student"
                      value="student"
                      v-model="user.accountType"
                    />
                    <label class="form-check-label" for="student"
                      >Student</label
                    >
                  </div>
                </div>
              </div>
            </div>
            <div class="col-6 my-1">
              <div class="form-group">
                <label class="h5" for="username">Username</label>
                <input
                  v-model="user.username"
                  type="text"
                  class="form-control p-2"
                  id="username"
                  placeholder="Username"
                  required="required"
                />
              </div>
            </div>
            <div class="col-6 my-1">
              <div class="form-group">
                <label class="h5" for="mail">E-mail</label>
                <input
                  v-model="user.mail"
                  type="email"
                  class="form-control p-2"
                  id="mail"
                  placeholder="email@exemple.com"
                  required="required"
                />
              </div>
            </div>
            <div class="col-12 my-1">
              <div class="row">
                <div class="col-6">
                  <div class="form-group">
                    <label class="h5" for="firstName">First name:</label>
                    <input
                      v-model="user.firstName"
                      type="text"
                      class="form-control"
                      id="firstName"
                      placeholder="Your first name"
                      required="required"
                    />
                  </div>
                </div>
                <div class="col-6">
                  <div class="form-group">
                    <label class="h5" for="secondName">Second name:</label>
                    <input
                      v-model="user.secondName"
                      type="text"
                      class="form-control"
                      id="secondName"
                      placeholder="Your second name"
                      required="required"
                    />
                  </div>
                </div>
              </div>
            </div>
            <div class="col-12 my-1">
              <div class="row">
                <div class="col-6">
                  <div class="form-group">
                    <label class="h5" for="password">Password</label>
                    <input
                      v-model="user.password"
                      type="password"
                      class="form-control"
                      id="password"
                      placeholder="password"
                      required="required"
                    />
                  </div>
                </div>
                <div class="col-6">
                  <div class="form-group">
                    <label class="h5" for="repassword">Confirm password</label>
                    <input
                      v-model="user.repassword"
                      type="password"
                      class="form-control"
                      id="repassword"
                      placeholder="password"
                      required="required"
                    />
                  </div>
                </div>
              </div>
            </div>
            <div class="col-12 my-1">
              <div class="form-group">
                <label class="h5" for="biography">Biography</label>
                <textarea
                  v-model="user.biography"
                  class="form-control p-4"
                  id="biography"
                  rows="3"
                  placeholder="Tell us something about you"
                  required="required"
                ></textarea>
              </div>
            </div>
            <div class="col-12 my-1">
              <div class="form-group">
                <button type="submit" class="btn btn-block  p-1">
                  Sign up
                </button>
              </div>
            </div>
            <div class="col-12 text-center my-1">
              <h4>
                Already have an account?
                <router-link
                  :to="{ name: 'Login', params: { login: true } }"
                  tag="a"
                  >Sign in</router-link
                >
              </h4>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SignUpForm",
  data() {
    return {
      user: {
        username: null,
        mail: null,
        firstName: null,
        secondName: null,
        password: null,
        repassword: null,
        biography: null,
        accountType:"student"
      },
      formErrors: []
    };
  },
  methods: {
    formValidation:function (e) {
        this.formErrors=[];
        if (!this.user.username) {
            this.formErrors.push("Username Cant Be Empty");
        }
        if (!this.user.mail) {
            this.formErrors.push(" E-mail Cant Be Empty");
        }
        if (!this.user.firstName) {
            this.formErrors.push("first name Cant Be Empty");
        }
        if (!this.user.secondName) {
            this.formErrors.push("second name  Cant Be Empty");
        }
        if (!this.user.password) {
            this.formErrors.push("password Cant Be Empty");
        }
        if (this.user.password!==this.user.repassword) {
            this.formErrors.push("Confirm your password");
        }
        if (!this.formErrors.length){
            this.$store.dispatch('auth/register', this.user).then(
                    this.$router.push(`/login/${true}`),
                error => {
                    this.message = (error.response && error.response.data) || error.message || error.toString();
                }
            );
            return true;
        }
        e.preventDefault();


    }
  }
};
</script>

<style scoped>
.login {
  border-radius: 2%;
  width: 90%;
}
.img-fluid {
  height: 150px;
  width: 150px;
}
form {
  width: 90%;
}
.form-control {
  background-color: #dbf4f6;
}
.btn-block {
  background-color: #efb932;
  font-weight: bold;
}
</style>
