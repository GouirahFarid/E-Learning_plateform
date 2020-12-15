<template>
  <div class="container-fluid lynda mx-3 my-5">
    <div class="row justify-content-around  ">
      <div class="col-5 left-cat row align-items-center justify-content-center">
        <div class="row">
            <div class="col-12 text-center" style="color: #faf8ff">
                <h1>Computer Science</h1>
            </div>
            <div class="col-12 text-center">
                <router-link class="btn btn-outline-primary btn-item" tag="button" :to='{name:"CategoryCourses",params:{id:2}}'>
                    <i class="mdi mdi-arrow-right-drop-circle-outline"></i>Courses
                </router-link>
            </div>
        </div>
      </div>
      <div class="col-7 right-cat rounded  ">
        <div class="row justify-content-around">
          <div class="col-5 right-cat-item img4 row align-items-center justify-content-center  my-1">
              <div class="row">
                  <div class="col-12 text-center" style="color: #faf8ff">
                      <h4 class="item-h">Design</h4>
                  </div>
                  <div class="col-12 text-center">
                      <router-link class="btn btn-primary btn-item" tag="button" :to='{name:"CategoryCourses",params:{id:4}}'>
                          <i class="mdi mdi-arrow-right-drop-circle-outline"></i>Courses
                      </router-link>
                  </div>
              </div>
          </div>
          <div class="col-5 right-cat-item img1 row align-items-center justify-content-center    my-1">
              <div class="row">
                  <div class="col-12 text-center" style="color: #faf8ff">
                      <h4 class="item-h">Data Science</h4>
                  </div>
                  <div class="col-12 text-center">
                      <router-link class="btn btn-primary btn-item" tag="button" :to='{name:"CategoryCourses",params:{id:3}}'>
                          <i class="mdi mdi-arrow-right-drop-circle-outline"></i>Courses
                      </router-link>
                  </div>
              </div>
          </div>
          <div class="col-5 right-cat-item img2 row align-items-center justify-content-center    my-1">
              <div class="row">
                  <div class="col-12 text-center" style="color: #faf8ff">
                      <h4 class="item-h">Business</h4>
                  </div>
                  <div class="col-12 text-center">
                      <router-link class="btn btn-primary btn-item" tag="button" :to='{name:"CategoryCourses",params:{id:1}}'>
                          <i class="mdi mdi-arrow-right-drop-circle-outline"></i>Courses
                      </router-link>
                  </div>
              </div>
          </div>
          <div class="col-5 right-cat-item img3 row align-items-center justify-content-center   my-1">
              <div class="row">
                  <div class="col-12 text-center" style="color: #faf8ff">
                      <h4 class="item-h">Language Learning</h4>
                  </div>
                  <div class="col-12 text-center">
                      <router-link class="btn btn-primary btn-item" tag="button" :to='{name:"CategoryCourses",params:{id:5}}'>
                          <i class="mdi mdi-arrow-right-drop-circle-outline"></i>Courses
                      </router-link>
                  </div>
              </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Lynda"
};
</script>

<style scoped>
.left-cat {
  background-image: url("../../assets/images/slide/slide.jpg");
  background-size: 100%;
}
.left-cat:hover{
    border: 3px solid #e08e03;
    border-top-left-radius: 20%;
    border-bottom-right-radius: 20%;
}
.img1 {
    background-image: url("../../assets/categories/2.png");
    background-size: 100%;}
.img2 {
    background-image: url("../../assets/categories/3.jpg");
    background-size: 100%;}
.img3 {
    background-image: url("../../assets/categories/4.jpg");
    background-size: 100%;}
.img4 {
    background-image: url("../../assets/categories/1.png");
    background-size: 100%;
}
.item-h{
    color:#feff18;
}
.btn-item{
    background-color: #ffad10;
    border: 0;
    color: #000000;
}
.right-cat-item{
  background-color: #993366;
  height: 160px;
  width: 100%;
}
.right-cat-item:hover{
    border: 3px solid #e08e03;
    border-top-left-radius: 20%;
    border-bottom-right-radius: 20%;
}
</style>
