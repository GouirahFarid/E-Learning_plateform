<template>
    <form>
        <div class="row">
            <div class="col-12 text-center my-2"><h1>Create Question</h1></div>
            <div class="col-12 my-2">
                <div class="form-group">
                    <label class="h5" for="oldDescription">Old Question:</label>
                    <textarea readonly
                            :value="oldQuestion.content"
                            class="form-control p-2"
                            id="oldDescription"
                            rows="2"
                            placeholder="description"
                            required="required"
                    ></textarea>
                </div>
            </div>
            <div class="col-12 my-1">
                <div class="form-group">
                    <label class="h5" for="description">Question:</label>
                    <textarea
                            v-model="NewQuestion.content"
                            class="form-control p-2"
                            id="description"
                            rows="3"
                            placeholder="description"
                            required="required"
                    ></textarea>
                </div>
            </div>
            <div class="col-12 my-2">
                <div class="form-group">
                    <label class="h5" for="oldMark">Question mark:</label>
                    <input type="text" readonly :value="oldQuestion.mark" class="form-control p-4" id="oldMark" required="required"/>
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
            oldQuestion:Object
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
                    question.append('questionId',this.oldQuestion.id)
                    this.$store.dispatch("formation/updateQuestion",question)
                    this.$router.push({name:'Profile',params:{type:this.$store.state.auth.userState.user.roles[0].roleName,account:'myCourses'}});
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