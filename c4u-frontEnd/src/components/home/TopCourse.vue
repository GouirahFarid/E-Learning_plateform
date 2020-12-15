<template>
  <div class="container my-5">
    <div class="row">
      <div class="col-12 h1  text-center">Browse Our Top Courses</div>
      <div class="col-12 my-2 text-center">
        <ul class="list-inline">
          <li @click='activate(1);getCurrentCategory(2)' class="list-inline-item mx-3" :class='{ active : active_el === 1 }'>Computer Science</li>
          <li @click='activate(2);getCurrentCategory(4)' class="list-inline-item mx-3 " :class='{ active : active_el === 2 }'>Design</li>
          <li @click='activate(3);getCurrentCategory(1)' class="list-inline-item mx-3" :class='{ active : active_el === 3 }'>Business</li>
          <li @click='activate(4);getCurrentCategory(5)' class="list-inline-item mx-3" :class='{ active : active_el === 4 }'>Language Learning</li>
          <li @click='activate(5);getCurrentCategory(3)' class="list-inline-item mx-3" :class='{ active : active_el === 5 }'>Data Science</li>
        </ul>
      </div>
      <div class="col-12" v-if="currentCategory!==null">
        <div class="row">
          <div class="col-4 my-3" v-for="(course,courseIndex) in currentCategory.courses" :key="course.id">
            <CardCourse v-if="courseIndex<6" :card-course="course"></CardCourse>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import CardCourse from "../CardCourse"
export default {
  name: "TopCourse",
  components:{CardCourse},
  data() {
    return {
      active_el: 1,
      currentCategory:null,
    };
  },
  methods: {
    activate(el) {
      this.active_el = el;
    },
    getCurrentCategory(categoryId){
      this.currentCategory=this.$store.state.formation.categories[categoryId];
    }
  },
  created() {
    this.getCurrentCategory(2);
  }
};
</script>

<style scoped>
.active {
  border-bottom: 5px #10c724 solid;
}
</style>
