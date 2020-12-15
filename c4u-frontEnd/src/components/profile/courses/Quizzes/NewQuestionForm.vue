<template>
    <form>
        <div class="row">
            <div class="col-12 text-center my-5"><h1>Create Question</h1></div>
            <div class="col-12 my-2">
                <div class="form-group">
                    <label class="h5" for="description">Question:</label>
                    <textarea
                            v-model="NewQuestion.content"
                            class="form-control p-4"
                            id="description"
                            rows="5"
                            placeholder="description"
                            required="required"
                    ></textarea>
                </div>
            </div>
            <div class="col-12 my-2">
                <div class="form-group">
                    <label class="h5" for="mark">Question mark:</label>
                    <input step="0.01"  type="number" v-model="NewQuestion.mark" class="form-control p-4" id="mark" required="required"/>
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
        name: "NewQuestionForm",
        props:{
            currentQuiz:Object
        },
        data(){
            return{
                NewQuestion:{
                    content:'',
                    mark:0
                },
                formErrors:[]
            }
        },
        methods:{
            formValidation() {
                if (!this.formErrors.length) {
                    let question = new FormData();
                    question.append('content', this.NewQuestion.content);
                    question.append('mark',this.NewQuestion.mark)
                    question.append('quizId',this.currentQuiz.id)
                    this.$store.dispatch("formation/createQuestion",question)
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