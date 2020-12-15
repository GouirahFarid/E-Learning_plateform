<template>
  <div class="home mx-0">
    <div class="container-fluid m-0 ">
      <div class="row">
        <div class="col-12 mx-0 px-0 slide mt-3">
          <div class="row align-items-center">
            <div class="col-6">
              <img class="img-fluid" src="../assets/images/C4U.png" alt="sdsd"/>
            </div>
            <div class="col-6">
              <h1>Learn from  your home</h1>
              <h4 class="w-75">Anywhere, anytime. Enjoy risk-free with our 30-day, money-back guarantee.</h4>
              <h6 style="color:#efb932">#Stay_Hungry_Stay_Foolish</h6>
            </div>
          </div>
        </div>
        <div class="col-12 slide-bottom py-4 text-light">
          <div class="row">
            <div class="col-4 text-center">
              <div class="row">
                <div class="col-4 text-right">
                  <svg
                    class="bi bi-cursor"
                    width="4em"
                    height="4em"
                    viewBox="0 0 16 16"
                    fill="currentColor"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M14.082 2.182a.5.5 0 0 1 .103.557L8.528 15.467a.5.5 0 0 1-.917-.007L5.57 10.694.803 8.652a.5.5 0 0 1-.006-.916l12.728-5.657a.5.5 0 0 1 .556.103zM2.25 8.184l3.897 1.67a.5.5 0 0 1 .262.263l1.67 3.897L12.743 3.52 2.25 8.184z"
                    />
                  </svg>
                </div>
                <div class="col-8 text-left">
                  <p class="h4 d-block">100,000 online course</p>
                  <p class="d-block">Explore a variety of fresh topics</p>
                </div>
              </div>
            </div>
            <div class="col-4 text-center">
              <div class="row">
                <div class="col-4 text-right">
                  <svg
                    class="bi bi-check2-circle"
                    width="4em"
                    height="4em"
                    viewBox="0 0 16 16"
                    fill="currentColor"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M15.354 2.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3-3a.5.5 0 1 1 .708-.708L8 9.293l6.646-6.647a.5.5 0 0 1 .708 0z"
                    />
                    <path
                      fill-rule="evenodd"
                      d="M8 2.5A5.5 5.5 0 1 0 13.5 8a.5.5 0 0 1 1 0 6.5 6.5 0 1 1-3.25-5.63.5.5 0 1 1-.5.865A5.472 5.472 0 0 0 8 2.5z"
                    />
                  </svg>
                </div>
                <div class="col-8 text-left">
                  <p class="h4 d-block">Expert instruction</p>
                  <p class="d-block">Find the right instructor for you</p>
                </div>
              </div>
            </div>
            <div class="col-4 text-center">
              <div class="row">
                <div class="col-4 text-right">
                  <svg
                    class="bi bi-bullseye"
                    width="4em"
                    height="4em"
                    viewBox="0 0 16 16"
                    fill="currentColor"
                    xmlns="http://www.w3.org/2000/svg"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"
                    />
                    <path
                      fill-rule="evenodd"
                      d="M8 13A5 5 0 1 0 8 3a5 5 0 0 0 0 10zm0 1A6 6 0 1 0 8 2a6 6 0 0 0 0 12z"
                    />
                    <path
                      fill-rule="evenodd"
                      d="M8 11a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm0 1a4 4 0 1 0 0-8 4 4 0 0 0 0 8z"
                    />
                    <path d="M9.5 8a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z" />
                  </svg>
                </div>
                <div class="col-8 text-left">
                  <p class="h4 d-block">Lifetime access</p>
                  <p class="d-block">Learn on your schedule</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <Lynda></Lynda>
    <LyndaBottom></LyndaBottom>
    <TopCourse></TopCourse>
    <div class="container">
      <div class="row justify-content-around">
        <div class="col-4"><CardCourse></CardCourse></div>
        <div class="col-4"><CardCourse></CardCourse></div>
        <div class="col-4"><CardCourse></CardCourse></div>
        <div class="col-4"><CardCourse></CardCourse></div>
        <div class="col-4"><CardCourse></CardCourse></div>
        <div class="col-4"><CardCourse></CardCourse></div>
      </div>
    </div>
  </div>
</template>

<script>
import Lynda from "../components/home/Lynda";
import LyndaBottom from "../components/home/LyndaBottom";
import TopCourse from "../components/home/TopCourse";
import CardCourse from "../components/CardCourse";
export default {
  name: "Home",
  components: {
    Lynda,
    LyndaBottom,
    TopCourse,
    CardCourse,
  },
  beforeCreate() {
    this.$store.dispatch("auth/getPerson")
  }
};
</script>
<style scoped>
.slide {
  background: #aa4b6b;
  background: linear-gradient(to bottom, #3b8d99, #6b6b83, #aa4b6b);

  height: 500px;
  width: 100%;
}
.slide-bottom {
  background: #536976;
  background: linear-gradient(to left, #292E49, #536976);

}
</style>
