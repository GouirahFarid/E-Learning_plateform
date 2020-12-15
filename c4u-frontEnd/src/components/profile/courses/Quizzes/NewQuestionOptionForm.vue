<template>
    <form>
        <div class="row">
            <div class="col-12 text-center my-5"><h1>Create choice</h1></div>
            <div class="col-12 my-2">
                <div class="form-group">
                    <label class="h5" for="description">Question:</label>
                    <textarea
                            v-model="NewQuestionOption.content"
                            class="form-control p-4"
                            id="description"
                            rows="5"
                            placeholder="description"
                            required="required"
                    ></textarea>
                </div>
            </div>
            <div class="col-12 my-2">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio"  id="inlineRadio1" :value="Number(1)" v-model="NewQuestionOption.isCorrect">
                    <label class="form-check-label" for="inlineRadio1">Correct</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="inlineRadio2" :value="Number(0)" v-model="NewQuestionOption.isCorrect">
                    <label class="form-check-label" for="inlineRadio2">Incorrect</label>
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
            currentQuestion:Object
        },
        data(){
            return{
                NewQuestionOption:{
                    content:'',
                    isCorrect:0
                },
                formErrors:[]
            }
        },
        methods:{
            formValidation() {
                if (!this.formErrors.length) {
                    let questionOption = new FormData();
                    questionOption.append('content', this.NewQuestionOption.content);
                    questionOption.append('isCorrect',Number(this.NewQuestionOption.isCorrect))
                    questionOption.append('questionId',this.currentQuestion.id)
                    this.$store.dispatch("formation/createQuestionOption",questionOption)
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