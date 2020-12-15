<template>
    <form  >
        <div class="row">
            <div class="col-12 text-center"><h1>Create Sub Chapter</h1></div>
            <div class="col-12 my-2">
                <div class="form-group">
                    <label class="h5" for="title">sub chapter title</label>
                    <input
                            v-model="NewSubChapter.title"
                            type="text"
                            class="form-control p-4"
                            id="title"
                            placeholder="Course title"
                            required="required"
                    />
                </div>
            </div>
            <div class="col-12">
                <div class="form-group">
                    <label for="image">Choose course video</label>
                    <input @change="previewFiles()" ref="myImage" type="file" class="form-control-file" id="image"/>
                </div>
            </div>
            <div class="col-12 my-2">
                <div class="form-group">
                    <label class="h5" for="description"> sub chapter Content</label>
                    <textarea v-model="NewSubChapter.content" class="form-control p-5" id="description"
                            rows="5" placeholder="content"
                            required="required"
                    ></textarea>
                </div>
            </div>
            <div class="col-12 text-center">
                <button  @click="formValidation" class="btn btn-primary">Save</button>
            </div>
        </div>
    </form>
</template>

<script>
    export default {
        name: "SubChapterForm",
        props:{
            currentChapter:Object
        },
        data(){
            return{
                NewSubChapter:{
                    title:'',
                    content:'',
                    video:''
                },
                formErrors:[]
            }
        },
        methods:{
            previewFiles() {
                this.NewSubChapter.video= this.$refs.myImage.files[0];
            },
           formValidation() {
                if (!this.formErrors.length) {
                    let subChapter = new FormData();
                    subChapter.append('file', this.NewSubChapter.video);
                    subChapter.append('title', this.NewSubChapter.title);
                    subChapter.append('content', this.NewSubChapter.content)
                    subChapter.append('chapterId', this.currentChapter.id)
                   this.$store.dispatch("formation/createSubChapter", subChapter);
                    this.$router.push({name: 'Profile', params: {type: this.$store.state.auth.userState.user.roles[0].roleName, account: 'account'}})
                    return  true
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