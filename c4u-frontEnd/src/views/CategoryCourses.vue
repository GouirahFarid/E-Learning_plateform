<template>
  <div class="container-fluid m-0 mt-2 p-0">
    <div class="row my-3">
        <div class="col-12 my-5 category-title text-center text-uppercase">
            <h1>{{$store.state.formation.categories[$route.params.id].title}}</h1>
        </div>
      <div v-if="category.courses.length>0" class="col-12 my-5">
          <div class="container">
              <div class="row">
                  <div class="col-4" v-for="course in category.courses" :key="course.id">
                      <CardCourse :card-course="course"></CardCourse>
                  </div>

              </div>
          </div>

      </div>
        <div v-else class="col-12 my-5 text-center">
            <h3 class="alert alert-danger">this category doesn't have courses</h3>
        </div>
    </div>
  </div>
</template>

<script>
import CardCourse from "../components/CardCourse";
export default {
  name: "CategoryCourses",
  components: { CardCourse },
    data(){
      return{
          category:this.$store.state.formation.categories[this.$route.params.id]
      }
    },
    watch:{
      '$route.params.id':function(id) {
          this.category=this.$store.state.formation.categories[id]
        }
    }
};
</script>

<style scoped>
    .category-title{
      background: #cc2b5e;
      background: linear-gradient(to left, #a361b3, #cc2b5e);


    }
</style>
