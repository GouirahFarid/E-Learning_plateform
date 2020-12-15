<template>
  <div class="m-0 p-0">
  <div class="row">
    <div class="col-12 my-2 text-center"><h2>Categories</h2></div>
    <div class="col-12 text-center my-4">
      <button @click="changeCategory(true)"
        class="btn btn-primary btn-block border-0" data-toggle="modal"
        data-target="#categoryModel" style="background-color: #ffc128;color: #030303"
      ><i class="mdi mdi-plus-circle-outline">create category</i></button>
    </div>
    <div v-if="categories.length > 0" class="col-12">
      <div class="table-responsive">
        <table class="table table-bordered table-hover">
          <thead>
            <tr>
              <th>#</th>
              <th>Category name</th>
              <th>Control</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(category, categoryIndex) in categories.slice(currentIndex,(currentIndex+step))" :key="category.id">
              <td>{{ categoryIndex+1 }}</td>
              <td>{{ category.title }}</td>
              <td class="text-center">
                  <i   @click="changeCategory(false);getCurrentCategory(index);"
                       data-toggle="modal"
                       data-target="#categoryModel"
                       class="mdi mdi-pencil-plus-outline mdi-36px mx-4" style="color: #24bd37"></i>
                  <i @click="deleteCategory(category.id)" class="mdi mdi-delete mdi-36px mx-4" style="color: #e51306"></i>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div v-if="categories.length > 0" class="col-12 text-center mt-3">
      <i @click="previousCategories" class="mdi mdi-chevron-left-circle mdi-36px  text-info mx-5"></i>
      <i @click="nextCategories" class="mdi mdi-chevron-right-circle mdi-36px text-info mx-5"></i>
    </div>
    <div v-if="categories.length <= 0" class="col-12  text-center">
      <h3 class="p-2 rounded-pill btn-outline-info my-2">
        there is no category
      </h3>
    </div>
    <!-- Modal  Categories -->
    <div
      class="modal fade"
      id="categoryModel"
      data-backdrop="static"
      data-keyboard="false"
      tabindex="-1"
      role="dialog"
      aria-labelledby="staticBackdropLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog  modal-dialog-scrollable ">
        <div class="modal-content">
          <div class="modal-header">
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <NewCategory v-if="isNewCategory"></NewCategory>
            <UpdateCategory
              v-if="!isNewCategory"
              :category="currentCategory"
            ></UpdateCategory>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal  Categories -->
  </div>
  </div>
</template>

<script>
import NewCategory from "./NewCategory";
import UpdateCategory from "./UpdateCategory";
export default {
  name: "Categories",
  components: { NewCategory, UpdateCategory },
  data() {
    return {
      categories: this.$store.state.formation.categories,
      currentCategory: null,
      isNewCategory: true,
      step:5,
      currentIndex:0,
    };
  },
  methods: {
    getCurrentCategory(idCategory) {
      this.currentCategory = this.categories[idCategory];
    },
    changeCategory(value) {
      this.isNewCategory = value;
    },
    deleteCategory(categoryId){
      let  category=new  FormData();
      category.append('categoryId',categoryId)
      this.$store.dispatch("formation/deleteCategory", category);
    },
    nextCategories(){
      if(this.currentIndex+this.step<(this.categories.length-1)){
        this.currentIndex+=this.step;
        this.step+=this.step
      }
    },
    previousCategories(){
      if (this.step-this.currentIndex>0){
        this.step-=this.currentIndex;
        this.currentIndex-=this.step;
      }
    }
  }
};
</script>

<style scoped>
  .modal-header{
    background: #24b0c2;
  }
  thead {
    background-color: #301b68;
    color: #edffff;
  }
</style>
