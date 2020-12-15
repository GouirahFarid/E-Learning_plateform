<template>
    <div class="container-fluid">
        <div class="row">
            <div class="col-3 profile-left pt-5 sticky-top fixed">
             <div class="row">
                 <div class="col-10 p-0 my-2">
                     <div class="row">
                         <div class="col-12 text-center">
                             <h1 class="acc p-0 my-0"><i class="mdi mdi-account-circle "></i></h1>
                         </div>
                         <div class="col-12 text-center my-0">
                             <h4>{{$store.state.auth.userState.user.username}}</h4>
                         </div>
                     </div>
                 </div>
                 <div class="col-11 left-font rounded-pill my-2" :class="{active:activeValue===1}"><h5  @click="changeAccount('account');getActive(1)"><i class="mdi mdi-badge-account-horizontal mdi-36px mx-2"></i> My Account</h5></div>
                 <div class="col-11 left-font rounded-pill my-2" :class="{active:activeValue===2}" ><h5  @click="changeAccount('myCourses');getActive(2)"><i class="mdi mdi-school mdi-36px mx-2"></i> My Courses</h5></div>
                 <div v-if="accountTyp==='ROLE_ADMIN'" class="col-11 my-2 left-font rounded-pill" :class="{active:activeValue===3}"><h5   @click="changeAccount('categories');getActive(3)"><i class="mdi mdi-school mdi-36px mx-2"></i>Categories</h5></div>

                 <div v-if="accountTyp==='ROLE_ADMIN'" class="col-11 my-2 left-font rounded-pill" :class="{active:activeValue===4}">
                     <h5  @click="changeAccount('users');getActive(4)"><i class="mdi mdi-google-classroom mdi-36px mx-2"></i> All users</h5>
                 </div>
                 <div class="col-11 my-2 left-font rounded-pill">
                     <h5 @click="logout"><i class="mdi mdi-logout mdi-36px mx-2"></i> Logout</h5>
                 </div>
             </div>
            </div>
            <div class="col-9 row justify-content-center profile-right my-5">
                <MyAccount v-if="account==='account'"></MyAccount>
                <MyCourses class="my-5 mx-2 px-2" v-if="account==='myCourses'"></MyCourses>
                <Categories v-if="accountTyp==='ROLE_ADMIN' && account==='categories'"></Categories>
                <Users v-if="account==='users'"></Users>
            </div>
        </div>
    </div>
</template>

<script>
    import  MyCourses from "../components/profile/courses/MyCourses"
    import  Categories from "../components/profile/category/Categories"
    import  MyAccount from "../components/profile/MyAccount"
    import  Users from "../components/profile/Users"
    export default {
        name: "ProfileView",
        components:{MyCourses,Categories,MyAccount,Users},
        data(){
            return{
                accountTyp:this.$route.params.type,
                myCourses:this.$store.state.auth.userState.user.courses,
                account:this.$route.params.account,
                activeValue:null,
            }
        },
        methods:{
            logout(){
                this.$store.dispatch('auth/logout').then(() => {this.$router.push("/");});
            },
            changeAccount(account){
                this.account=account;
            },
            getActive(value){
                this.activeValue=value;
            }
        },
        watch:{
            '$route.params.account':function (account) {
                alert('sdbhsd'+account)
                this.account=account;
                this.$store.dispatch('auth/getPerson')
            }
        },
        mounted() {
            if (this.$store.state.auth.userState.user.roles[0].roleName==='ROLE_ADMIN'){
                this.$store.dispatch("auth/getAdmins");
                this.$store.dispatch("auth/getProfessors");
                this.$store.dispatch("auth/getStudents");
            }
        },

    }
</script>

<style scoped>
.acc{
    font-size: 100px;
}
.profile-left{
    background: #1a2980;
    height: 400px;
    background: linear-gradient(to top, #1a2980, #26d0ce);
    border-bottom-right-radius: 50%;
    border-top-right-radius: 50%;
    margin-top: 40px;
}
    .profile-left{
        position: sticky;
        height: 800px;
    }
    .left-font:hover{
        background-color: #ffc128;
    }
    .active{
        background-color: #ffc128;
    }
</style>