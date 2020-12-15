<template>
  <form>
    <div class="row">
      <div class="col-12 text-center"><h3> course</h3></div>
      <div class="col-12">
          <label>New Categories:</label>
          <v-select style=" background-color: #c4edf6;" multiple v-model="NewCourse.categoriesId" :options="options" label="title" :reduce="option => option.catId"></v-select>
      </div>
      <div class="col-12 my-1">
        <div class="form-group">
          <label class="h5" for="oldTitle">Old Course title</label>
          <input readonly
            :value="currentCourse.title"
            type="text"
            class="form-control p-2"
            id="oldTitle"
            placeholder="Course title"
            required="required"
          />
        </div>
      </div>
      <div class="col-12 my-1">
        <div class="form-group">
          <label class="h5" for="title">New Course title(<span  class="mx-1" style="font-size: 24px;color: red">*</span>):</label>
          <input
                  v-model="NewCourse.title"
                  type="text"
                  class="form-control p-2"
                  id="title"
                  placeholder="Course title"
                  required="required"
          />
        </div>
      </div>
      <div class="col-12">
        <div class="form-group">
          <label for="oldimage">Old course image</label>
          <input readonly
                 :value="currentCourse.courseResource.fileName"
            ref="myImage"
            type="text"
            class="form-control"
            id="oldimage"
          />
        </div>
      </div>
      <div class="col-12">
        <div class="form-group">
          <label for="image">Choose course image</label>
          <input
                  @change="previewFiles()"
                  ref="myImage"
                  type="file"
                  class="form-control"
                  id="image"
          />
        </div>
      </div>
      <div class="col-12 my-1">
        <div class="form-group">
          <label class="h5" for="oldDescription">Old Description</label>
          <textarea
                  readonly
            :value="currentCourse.description"
            class="form-control p-2"
            id="oldDescription"
            rows="2"
            placeholder="description"
            required="required"
          ></textarea>
        </div>
      </div>
      <div class="col-12 my-1">
        <div class="form-group">
          <label class="h5" for="description">New Description(<span  class="mx-1" style="font-size: 24px;color: red">*</span>):</label>
          <textarea
                  v-model="NewCourse.description"
                  class="form-control p-2"
                  id="description"
                  rows="2"
                  placeholder="description"
                  required="required"
          ></textarea>
        </div>
      </div>
      <div class="col-12 text-center">
        <button @click="formValidation" style="background-color: #ffc128;color: #030303"  class="btn btn-block">Save</button>
      </div>
    </div>
  </form>
</template>

<script>
export default {
  name: "CourseForm",
  props:{
    currentCourse:Object
  },

  data() {
    return {
      categories: this.$store.state.formation.categories,
      currentUserId:this.$store.state.auth.userState.user.id,
        options:[],
      NewCourse: {
        title: "",
        description: "",
        image: "",
        categoriesId:[]
      },
      formErrors: []
    };
  },

  methods: {
    formValidation() {
      if (!this.formErrors.length) {
        let course = new FormData();
        course.append('file', this.NewCourse.image);
        course.append('title', this.NewCourse.title);
        course.append('description', this.NewCourse.description);
        course.append('categoriesId',this.NewCourse.categoriesId);
        course.append('courseId',this.currentCourse.id)
        this.$store.dispatch("formation/updateCourse",course)
          .then(this.$router.push({name:'Profile',params:{type:this.$store.state.auth.userState.user.roles[0].roleName,account:'myCourses'}}));
        return true;
      }
    },
    previewFiles() {
      this.NewCourse.image = this.$refs.myImage.files[0];
    },
      getCategories(){
          this.categories.forEach(category=>this.options.push({title:category.title,catId:category.id}))

      }
  },
  mounted() {
      this.getCategories();
    }
};
</script>

<style scoped>
  .form-control{
    background-color: #c4edf6;
  }
</style>
