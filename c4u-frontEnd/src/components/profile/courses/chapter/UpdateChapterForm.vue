<template>
    <form>
    <div class="row">
        <div class="col-12 my-2 text-center"><h2>Update chapter</h2></div>
        <div class="col-12 my-1">
            <div class="form-group">
                <label class="h5" for="oldTitle">Old Chapter Tile</label>
                <input readonly :value="currentChapter.title" type="text" class="form-control p-2" id="oldTitle" placeholder="chapter title" required="required"/>
            </div>
        </div>
        <div class="col-12 my-1">
            <div class="form-group">
                <label class="h5" for="title">Chapter Tile</label>
                <input v-model="chapter.title" type="text" class="form-control p-2" id="title" placeholder="chapter title" required="required"/>
            </div>
        </div>
        <div class="col-12 my-1">
            <div class="form-group">
                <label class="h5" for="oldDescription">Description</label>
                <textarea readonly :value="currentChapter.description" class="form-control p-2" id="oldDescription" rows="2" placeholder="description"
                          required="required"
                ></textarea>
            </div>
        </div>
        <div class="col-12 my-1">
            <div class="form-group">
                <label class="h5" for="description">Description</label>
                <textarea v-model="chapter.description" class="form-control p-2" id="description" rows="2" placeholder="description"
                          required="required"
                ></textarea>
            </div>
        </div>
        <div class="col-12 text-center">
            <button @click="formValidation" style="background-color: #ffc128;color: #030303" class="btn btn-block mx-2">Save chapter</button>
        </div>
    </div>
    </form>
</template>

<script>
    export default {
        name: "NewChapterForm",
        props:{
            currentChapter:Object
        },
        data(){
           return{
               chapter:{
                   title:null,
                   description:null,
               },
               formErrors:[]

           }
        },
        methods:{
            formValidation() {
                if (!this.formErrors.length) {
                    let chapter = new FormData();
                    chapter.append('title',this.chapter.title)
                    chapter.append('description',this.chapter.description)
                    chapter.append('chapterId',this.currentChapter.id)
                    this.$store.dispatch("formation/updateChapter",chapter);
                    this.$router.push({name:'Profile',params:{type:this.$store.state.auth.userState.user.roles[0].roleName,account:'account'}});
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