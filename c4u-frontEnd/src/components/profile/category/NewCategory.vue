<template>
    <form @submit="formValidation">
    <div class="row">
        <div class="col-12 alert alert-danger" v-if="formErrors.length>0">
            <ul>
                <li v-for="error in formErrors" :key="error">{{error}}</li>
            </ul>
        </div>
        <div class="col-12 my-2">
            <div class="form-group">
                <label class="h5" for="title">Category title(<span  class="mx-1" style="font-size: 24px;color: red">*</span>):</label>
                <input v-model="category.title" type="text" class="form-control p-4" id="title" placeholder="Category Title" required="required"
                />
            </div>
        </div>
        <div class="col-12 my-2">
            <div class="form-group">
                <label class="h5" for="biography">Category description(<span  class="mx-1" style="font-size: 24px;color: red">*</span>):</label>
                <textarea v-model="category.description" class="form-control p-4" id="biography" rows="5" placeholder="description"
                          required="required"
                ></textarea>
            </div>
        </div>
        <div class="col-12 my-2 text-center">
            <button class="btn btn-block" style="background-color: #ffc128;color: #030303">Create category</button>
        </div>
    </div>
    </form>
</template>

<script>
    export default {
        name: "NewCategory",
        data(){
            return{
                category:{
                    title:null,
                    description:null
                },
                formErrors: []
            }
        },
        methods:{
            formValidation(){
                if (!this.category.title) {
                    this.formErrors.push("title cannot be empty");
                }
                if (!this.category.description) {
                    this.formErrors.push("description cannot be empty");
                }
                if (!this.formErrors.length){
                    this.$store.dispatch('formation/createCategory', this.category);
                }
            }
        }
    }
</script>

<style scoped>
    .form-control{
        background-color: #c4edf6;
    }
</style>