<template>
    <div>
        <div class="row">
            <div class="col-12 text-center"><h1>{{courseQuizzes.title}} quizzes</h1></div>
            <div class="col-12">
                <button @click="changeQuiz(true);getCurrentForm('quiz')" style="background-color: #ffc128;" class="btn btn-block border-0 my-2" data-toggle="modal" data-target="#quizEdit">Create quiz</button>
            </div>
            <div class="col-12">
                <div class="table-responsive">
                    <table class=" table table-bordered table-bordered">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Quiz Title</th>
                            <th>Control</th>
                        </tr>
                        </thead>
                        <tbody>
                         <tr v-for="(quiz ,quizIndex) in courseQuizzes.quizzes" :key="quiz.id">
                             <td>{{quizIndex+1}}</td>
                             <td>{{quiz.title}}</td>
                             <td class="text-center">
                                 <button @click="getCurrentQuiz(quizIndex)" data-toggle="modal" data-target="#quizContent"
                                         class="btn btn-primary">More...</button>
                                 <button @click="changeQuiz(false);getCurrentQuiz(quizIndex);getCurrentForm('quiz')"
                                         data-toggle="modal" data-target="#quizEdit"
                                         class="btn btn-success mx-4"><i class="mdi mdi-pencil-plus-outline"></i></button>
                                 <button @click="deleteQuiz(quiz.id)" class="btn btn-danger"><i class="mdi mdi-delete"></i> </button>
                             </td>
                         </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-12 text-center mt-3">
                <i @click="previousQuizzes" class="mdi mdi-chevron-left-circle mdi-36px  text-info mx-5"></i>
                <i @click="nextQuizzes" class="mdi mdi-chevron-right-circle mdi-36px text-info mx-5"></i>
            </div>
        </div>

        <!-- Quiz content -->
        <div v-if="currentQuiz" class="modal fade" id="quizContent" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl modal-dialog-scrollable" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                       <div class="row">
                           <div class="col-12 my-3 text-center">
                               <button @click="changeQuestion(true);getCurrentForm('question')"
                                       data-toggle="modal" data-target="#quizEdit"
                                        class="btn btn-block btn-outline-success">
                                   <i class="mdi mdi-plus-thick"> add question</i>
                               </button>
                           </div>
                           <div class="col-12 " v-for="(question,questionIndex) in currentQuiz.questions" :key="question.id">
                               <div class="row question my-2 py-2 rounded-pill">
                                   <div class="col-8"
                                        data-toggle="collapse" :href="'#question'+questionIndex"
                                        role="button" aria-expanded="false" :aria-controls="'question'+questionIndex"
                                   ><h2 class="btn">{{question.content}}</h2></div>
                                   <div class="col-4 text-left">
                                       <button class="btn btn-primary"
                                               data-toggle="modal"
                                               data-target="#quizEdit"
                                               @click="changeQuestionOption(true);getCurrentForm('questionOption');getCurrentQuestion(questionIndex)"
                                       ><i class="mdi mdi-plus-thick"></i></button>
                                       <button @click="getCurrentQuestion(questionIndex); getCurrentForm('question'); changeQuestion(false)"
                                               class="btn btn-success mx-1"
                                               data-toggle="modal"
                                               data-target="#quizEdit"
                                       ><i class="mdi mdi-pencil-plus-outline"></i></button>
                                       <button @click="deleteQuestion(question.id)" class="btn btn-danger "><i class="mdi mdi-delete"></i></button>
                                   </div>
                               </div>
                               <div class="collapse" :id="'question'+questionIndex">
                                   <div class="card card-body rounded-0 border-0">
                                       <div class="row">
                                           <div class="col-12" v-for="(questionOption ,questionOptionIndex) in question.questionOptions" :key="questionOption.id">
                                               <div class="row choice my-2 py-2 rounded-pill">
                                                   <div class="col-8 pl-5 mt-2">{{questionOption.content}}</div>
                                                   <div class="col-4">
                                                       <button @click="deleteQuestionOption(questionOption.id)" class="btn btn-danger mx-1"><i class="mdi mdi-delete"></i></button>
                                                       <button @click="getCurrentQuestionOption(questionIndex,questionOptionIndex);
                                                                       getCurrentForm('questionOption');
                                                                       changeQuestionOption(false)"
                                                               class="btn btn-primary mx-1"
                                                               data-toggle="modal"
                                                               data-target="#quizEdit">
                                                           <i class="mdi mdi-pencil-plus-outline"></i>
                                                       </button>
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                               </div>
                           </div>
                       </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Quiz content -->

        <!-- Quiz Edit -->
        <div class="modal fade" id="quizEdit" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <NewQuizForm v-if="isNewQuiz && currentForm==='quiz'" :current-course="courseQuizzes"></NewQuizForm>
                        <UpdateQuizForm v-if="!isNewQuiz && currentForm==='quiz'" :current-quiz="currentQuiz"></UpdateQuizForm>
                        <NewQuestionForm v-if="isNewQuestion && currentForm==='question'" :current-quiz="currentQuiz"></NewQuestionForm>
                        <UpdateQuestionForm v-if="!isNewQuestion && currentForm==='question'" :old-question="currentQuestion"></UpdateQuestionForm>
                        <NewQuestionOptionForm v-if="isNewQuestionOption &&currentForm==='questionOption'" :current-question="currentQuestion"></NewQuestionOptionForm>
                        <UpdateQuestionOptionForm v-if="!isNewQuestionOption &&currentForm==='questionOption'" :current-question-option="currentQuestionOption"></UpdateQuestionOptionForm>
                    </div>
                </div>
            </div>
        </div>
        <!-- Quiz Edit -->
    </div>
</template>

<script>
    import  NewQuizForm from '../Quizzes/NewQuizForm'
    import  UpdateQuizForm from '../Quizzes/UpdateQuizForm'
    import  NewQuestionForm from "../Quizzes/NewQuestionForm"
    import  UpdateQuestionForm  from "../Quizzes/UpdateQuestionForm"
    import  NewQuestionOptionForm from  "../Quizzes/NewQuestionOptionForm"
    import  UpdateQuestionOptionForm from  "../Quizzes/UpdateQuestionOptionForm"
    export default {
        name: "CourseQuizzes",
        props:{
            courseQuizzes:Object
        },
        data(){
            return{
                isNewQuiz:true,
                currentQuiz:'',
                currentQuestion:null,
                currentQuestionOption:null,
                currentForm:'',
                isNewQuestion:'',
                isNewQuestionOption:'',
                step:5,
                currentIndex:0,
            }
        },
        components:{
            NewQuizForm,UpdateQuizForm,NewQuestionForm,UpdateQuestionForm,NewQuestionOptionForm,UpdateQuestionOptionForm
        },
        methods:{
            changeQuiz(value){
                this.isNewQuiz=value;
            },
            getCurrentQuiz(quizId){
                this.currentQuiz=this.courseQuizzes.quizzes[quizId];
            },
            getCurrentForm(value){
                this.currentForm=value
            },
            changeQuestion(value){
                this.isNewQuestion=value;
            },
            changeQuestionOption(value){
                this.isNewQuestionOption=value;
            },
            getCurrentQuestion(questionId){
                this.currentQuestion=this.currentQuiz.questions[questionId];
            },
            getCurrentQuestionOption(questionId,questionOptionId){
                this.currentQuestionOption=this.currentQuiz.questions[questionId].questionOptions[questionOptionId];
            },
            deleteQuiz(quizId){
                let  quiz=new  FormData();
                quiz.append('quizId',quizId)
                this.$confirm("Are you sure?").then(() => {this.$store.dispatch("formation/deleteQuiz", quiz)});
                this.$router.push({name:'Profile',params:{type:this.$store.state.auth.userState.user.roles[0].roleName,account:'account'}});
            },
            deleteQuestion(questionId){
                let  question=new  FormData();
                question.append('questionId',questionId)
                this.$confirm("Are you sure?").then(() => {this.$store.dispatch("formation/deleteQuestion", question)});
                this.$router.push({name:'Profile',params:{type:this.$store.state.auth.userState.user.roles[0].roleName,account:'account'}});
            },
            deleteQuestionOption(questionOptionId){
                let  questionOption=new  FormData();
                questionOption.append('questionOptionId',questionOptionId);
                this.$confirm("Are you sure?").then(() => {this.$store.dispatch("formation/deleteQuestionOption", questionOption)});
                this.$router.push({name:'Profile',params:{type:this.$store.state.auth.userState.user.roles[0].roleName,account:'account'}});
            },
            nextQuizzes(){
                if(this.currentIndex+this.step<(this.courseQuizzes.length-1)){
                    this.currentIndex+=this.step;
                    this.step+=this.step
                }
            },
            previousQuizzes(){
                if (this.step-this.currentIndex>0){
                    this.step-=this.currentIndex;
                    this.currentIndex-=this.step;
                }
            }
        }
    }
</script>

<style scoped>
    .modal-header{
        background: #24b0c2;
    }
    thead {
        background-color: #301b68;
        color: #edffff;
    }
    .question{
        background: #4ca1af;
        background: linear-gradient(to right, #4ca1af, #c4e0e5);

    }
    .choice{
        background: #00c6ff;
        background: linear-gradient(to right, #0072ff, #00c6ff);

    }
</style>