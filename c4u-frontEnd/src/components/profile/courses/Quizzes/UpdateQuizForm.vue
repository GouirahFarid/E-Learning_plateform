<template>
    <form>
        <div class="row">
            <div class="col-12 text-center  my-3"><h1>Create Quiz</h1></div>
            <div class="col-12 my-1">
                <div class="form-group">
                    <label class="h5" for="oldTitle">Old Quiz title</label>
                    <input :value="currentQuiz.title" type="text" class="form-control p-2"
                           id="oldTitle"
                           placeholder="Course title"
                           required="required"
                           readonly
                    />
                </div>
            </div>
            <div class="col-12 my-1">
                <div class="form-group">
                    <label class="h5" for="title">Quiz title</label>
                    <input v-model="NewQuiz.title" type="text" class="form-control p-2"
                           id="title"
                           placeholder="Course title"
                           required="required"
                    />
                </div>
            </div>
            <div class="col-12 my-1">
                <div class="form-group">
                    <label class="h5" for="oldDescription">Old description</label>
                    <textarea
                            :value="currentQuiz.description"
                            class="form-control p-1"
                            id="oldDescription"
                            rows="3"
                            placeholder="description"
                            required="required"
                            readonly
                    ></textarea>
                </div>
            </div>
            <div class="col-12 my-1">
                <div class="form-group">
                    <label class="h5" for="description">Description</label>
                    <textarea
                            v-model="NewQuiz.description"
                            class="form-control p-1"
                            id="description"
                            rows="3"
                            placeholder="description"
                            required="required"
                    ></textarea>
                </div>
            </div>
            <div class="col-12 text-center">
                <button @click="formValidation" class="btn btn-primary">Save</button>
            </div>
        </div>
    </form>
</template>

<script>
    export default {
        name: "NewQuizForm",
        props:{
            currentQuiz:Object
        },
        data(){
            return{
                NewQuiz:{
                    title:'',
                    description:''
                },
                formErrors:[]
            }
        },
        methods:{
            formValidation() {
                if (!this.formErrors.length) {
                    let quiz = new FormData();
                    quiz.append('title', this.NewQuiz.title);
                    quiz.append('description', this.NewQuiz.description);
                    quiz.append('quizId',this.currentQuiz.id)
                    this.$store.dispatch("formation/updateQuiz",quiz)
                    this.$router.push({name:'Profile',params:{type:this.$store.state.auth.userState.user.roles[0].roleName,account:'account'}});
                    return true;
                }
            },
        }
    }
</script>

<style scoped>
    .form-control{
        background-color: #c4edf6;
    }
</style>