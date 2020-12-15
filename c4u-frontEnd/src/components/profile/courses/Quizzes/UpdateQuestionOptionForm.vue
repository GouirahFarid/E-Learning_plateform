<template>
    <form>
        <div class="row">
            <div class="col-12 text-center my-2"><h1>Update choice</h1></div>
            <div class="col-12 my-2">
                <div class="form-group">
                    <label class="h5" for="oldDescription">Question:</label>
                    <textarea readonly
                            v-model="currentQuestionOption.content"
                            class="form-control p-2"
                            id="oldDescription"
                            rows="3"
                            placeholder="description"
                            required="required"
                    ></textarea>
                </div>
            </div>
            <div class="col-12 my-2">
                <div class="form-group">
                    <label class="h5" for="description">Question:</label>
                    <textarea
                            v-model="NewQuestionOption.content"
                            class="form-control p-2"
                            id="description"
                            rows="3"
                            placeholder="description"
                            required="required"
                    ></textarea>
                </div>
            </div>
            <div class="col-12 my-2">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio"  id="inlineRadio1" :value="true" v-model="NewQuestionOption.isCorrect">
                    <label class="form-check-label" for="inlineRadio1">Correct</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="inlineRadio2" :value="false" v-model="NewQuestionOption.isCorrect">
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
            currentQuestionOption:Object
        },
        data(){
            return{
                NewQuestionOption:{
                    content:'',
                    isCorrect:true
                },
                formErrors:[]
            }
        },
        methods:{
            formValidation() {
                if (!this.formErrors.length) {
                    let questionOption = new FormData();
                    questionOption.append('content', this.NewQuestionOption.content);
                    questionOption.append('isCorrect',this.NewQuestionOption.isCorrect)
                    questionOption.append('questionOptionId',this.currentQuestionOption.id)
                    this.$store.dispatch("formation/updateQuestionOption",questionOption)
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