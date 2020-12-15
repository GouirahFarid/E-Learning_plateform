<template>
    <form action="" method="post" @submit="formValidation">
    <div class="row">
        <div class="col-12 text-center">
            <h4>Update Category</h4>
        </div>
        <div class="col-12 alert alert-danger" v-if="formErrors.length>0">
            <ul>
                <li v-for="error in formErrors" :key="error">{{error}}</li>
            </ul>
        </div>
        <div class="col-12 my-1">
            <div class="form-group">
                <label class="h5" for="oldTitle">Old Category title</label>
                <input readonly :value="category.title" type="text" class="form-control p-4" id="oldTitle"/>
            </div>
        </div>
        <div class="col-12 my-1">
            <div class="form-group">
                <label class="h5" for="title">New Category title(<span  class="mx-1" style="font-size: 24px;color: red">*</span>):</label>
                <input v-model="newCategory.title" type="text" class="form-control p-4" id="title" placeholder="Category Title" required="required"
                />
            </div>
        </div>
        <div class="col-12 my-1">
            <div class="form-group">
                <label class="h5" for="oldDescription">Old Category description</label>
                <textarea readonly :value="category.description" class="form-control p-1" id="oldDescription" rows="3" placeholder="description"
                ></textarea>
            </div>
        </div>
        <div class="col-12 my-1">
            <div class="form-group">
                <label class="h5" for="biography">Category description(<span  class="mx-1" style="font-size: 24px;color: red">*</span>):</label>
                <textarea v-model="newCategory.description" class="form-control p-1" id="biography" rows="3" placeholder="description"
                          required="required"
                ></textarea>
            </div>
        </div>
        <div class="col-12 my-1 text-center">
            <button class="btn btn-block" style="background-color: #ffc128;color: #030303" type="submit">Update category</button>
        </div>
    </div>
    </form>
</template>

<script>
    export default {
        name: "UpdateCategory",
        props:{
            category:{
                type:Object
            }
        },
        data(){
            return{
                newCategory:{
                    title:'',
                    description:''
                },
                formErrors: []
            }
        },
        methods:{
            formValidation(){
                if (!this.formErrors.length){
                    let updateCategory=new  FormData();
                    updateCategory.append('title',this.newCategory.title)
                    updateCategory.append('description',this.newCategory.description)
                    updateCategory.append('categoryId',this.category.id)
                    this.$store.dispatch('formation/updateCategory',updateCategory).then(
                        this.$router.push(`/`),
                        error => {
                            this.message = (error.response && error.response.data) || error.message || error.toString();
                        }
                    );
                    return true;
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