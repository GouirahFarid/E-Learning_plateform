<template>
  <form>
    <div class="row">
      <div class="col-12 text-center"><h3>Create course</h3></div>
      <div class="col-12">
          <label>Categories(<span  class="mx-1" style="font-size: 24px;color: red">*</span>):</label>
          <v-select style="background-color:#c4edf6;" multiple v-model="NewCourse.categoriesId" :options="options" label="title" :reduce="option => option.catId"></v-select>
      </div>
      <div class="col-12 my-2">
        <div class="form-group">
          <label class="h5" for="title">Course title(<span  class="mx-1" style="font-size: 24px;color: red">*</span>):</label>
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
          <label for="image">Choose image(<span  class="mx-1" style="font-size: 24px;color: red">*</span>):</label>
          <input
            @change="previewFiles()"
            ref="myImage"
            type="file"
            class="form-control-file"
            id="image"
          />
        </div>
      </div>
      <div class="col-12 my-1">
        <div class="form-group">
          <label class="h5" for="description">Description(<span  class="mx-1" style="font-size: 24px;color: red">*</span>):</label>
          <textarea
            v-model="NewCourse.description"
            class="form-control p-2"
            id="description"
            rows="3"
            placeholder="description"
            required="required"
          ></textarea>
        </div>
      </div>
      <div class="col-12">
        <button  @click="formValidation" style="background-color: #ffc128;color: #030303" class="btn btn-primary btn-block border-0" data-dismiss="modal">Create</button>
      </div>
    </div>
  </form>
</template>

<script>
export default {
  name: "CourseForm",

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
        course.append('personId',this.currentUserId)
        this.$store.dispatch("formation/createCourse",course);
        this.$router.push( {name:'Profile',params:{type:this.$store.state.auth.userState.user.roles[0].roleName,account:"account"}});
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
