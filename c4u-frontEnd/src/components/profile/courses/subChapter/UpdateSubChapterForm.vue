<template>
    <form>
        <div class="row">
            <div class="col-12 text-center"><h1>Update Sub Chapter</h1></div>
            <div class="col-12 my-2">
                <div class="form-group">
                    <label class="h5" for="oldTitle">old sub chapter title</label>
                    <input readonly
                            v-model="currentSubChapter.title"
                            type="text"
                            class="form-control p-4"
                            id="oldTitle"
                            placeholder="Course title"
                            required="required"
                    />
                </div>
            </div>
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
                    <label for="oldImage">Old video</label>
                    <input readonly
                           type="text"
                           :value="currentSubChapter.courseResources[0].fileName"
                           class="form-control-file"
                           id="oldImage"
                    />
                </div>
            </div>
            <div class="col-12">
                <div class="form-group">
                    <label for="image">Choose course video</label>
                    <input
                            @change="previewFiles()"
                            ref="myImage"
                            type="file"
                            class="form-control-file"
                            id="image"
                    />
                </div>
            </div>
            <div class="col-12 my-2">
                <div class="form-group">
                    <label class="h5" for="oldDescription"> Old sub chapter Content</label>
                    <textarea readonly :value="currentSubChapter.content" class="form-control p-5" id="oldDescription"
                            rows="5" placeholder="content"
                            required="required"
                    ></textarea>
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
            <div class="col-12">
                <button @click="formValidation" class="btn btn-primary">Save</button>
            </div>
        </div>
    </form>
</template>

<script>
    export default {
        name: "SubChapterForm",
        props:{
            currentSubChapter:Object
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
                    let subChapter= new FormData();
                    subChapter.append('file', this.NewSubChapter.video);
                    subChapter.append('title', this.NewSubChapter.title);
                    subChapter.append('content', this.NewSubChapter.content)
                    subChapter.append('subChapterId',this.currentSubChapter.id)
                    this.$store.dispatch("formation/updateSubChapter",subChapter);
                    this.$router.push({name:'Profile',params:{type:this.$store.state.auth.userState.user.roles[0].roleName,account:'account'}});
                    return true;
                }
            }
        }
    }
</script>

<style scoped>

</style>