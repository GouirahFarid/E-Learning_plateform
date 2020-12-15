<template>
  <div class="container my-5">
    <div class="row">
      <div class="col-12 text-center mt-4">
        <div class="btn-group " role="group" aria-label="Basic example">
          <button
            @click="getPreviousChapter"
            type="button"
            class="btn btn-nav px-5"
          >
            <i class="mdi mdi-arrow-left-thick"></i>
          </button>
          <button type="button" class="btn btn-nav px-5">{{currentCourse[0].title}}</button>
          <button @click="getNextChapter()" type="button" class="btn btn-nav px-5">
            <i class="mdi mdi-arrow-right-thick"></i>
          </button>
        </div>
      </div>
      <div class="col-12 text-left pl-5 mt-3" v-if="currentChapter!==null">
        <h5 class="mx-3">
          {{ currentChapter.title }}<i class="mdi mdi-send mx-2"></i>
          {{ currentSubChapter.title }}
        </h5>
      </div>
      <div class="col-12 text-center" v-if="currentSubChapter!==null">
        <vue-core-video-player class="video" :src="require(`../assets/videos/${currentSubChapter.courseResources[0].fileName}`)"
        ></vue-core-video-player>
      </div>
      <div class="col-12 text-center mt-3" v-if="currentSubChapter!==null">
        <i  @click="getPreviousSubChapter" class="mdi mdi-chevron-left-circle mdi-36px float-left text-info ml-3"
        ></i>
        <button
          @click="getCurrentSubChapter(subChapterIndex)"
          v-for="(subChapter, subChapterIndex) in currentChapter.subChapters"
          :key="subChapter.id"
          class="subBtn bg-info border-0 rounded-pill mx-2"
          data-toggle="tooltip"
          data-placement="top"
          :title="subChapter.title"
        ></button>
        <i
          @click="getNextSubChapter"
          class="mdi mdi-chevron-right-circle mdi-36px text-info float-right mr-3"
        ></i>
      </div>
      <div class="col-12 quiz">
        <div class="row">
          <div class="col-12 text-center my-3"><h2>Course Quizzes</h2></div>
          <div class="col-12 text-center my-3">
            <button @click="activateEl(quizIndex);getCurrentQuiz(quizIndex);"
                    class="btn btn-outline-primary mx-3 px-4 rounded-pill"
                    v-for="(quiz,quizIndex) in currentCourse[0].quizzes" :key="quiz.id"
                    :class='{ active : active_el === quizIndex }'
            >Quiz {{quizIndex+1}}
            </button>
          </div>
          <div class="col-12 questions" v-if="currentQuiz!==null && !isPassed">
            <div class="row">
              <div class="col-12" v-for="(question,questionIndex) in currentQuiz.questions" :key="question.id">
                <div class="card my-2" style="box-shadow:3px 5px 5px  #565656 ">
                  <div class="card-body">
                    <h5 class="card-title py-2 my-3">Question {{questionIndex+1}}</h5>
                    <h5 class="card-subtitle my-3">{{question.content}}</h5>
                    <div class="form-check my-2" v-for="(questionOption,questionOptionIndex) in question.questionOptions" :key="questionOption.id">
                      <input class="form-check-input" type="radio"
                             @change="getAnswer($event,questionIndex,questionOptionIndex)"
                             :value="questionOption.isCorrect===1"
                             :checked="checkedAnswer[Number(questionIndex)]===Number(questionOptionIndex)"
                      >
                      <label class="form-check-label">{{questionOption.content}}</label>
                    </div>
                  </div>
                </div>
              </div>
              <div @click="submitQuiz" class="col-12 my-2"><button class="btn btn-block btn-outline-primary">Submit</button> </div>
            </div>
          </div>
          <div class="col-12 row justify-content-center" v-if="isPassed">
              <div v-if="(passedQuiz[0].note/numb*100)>60" class="card  card-success text-center my-3">
                  <div class="card-body">
                      <h2>Nice! You passed this quiz!</h2>
                      <h3>{{(passedQuiz[0].note/numb)*100}}%</h3>
                      <i class="mdi mdi-check-bold mdi-48px" style="color: #149c09"></i>
                  </div>
              </div>
              <div v-if="(passedQuiz[0].note/numb*100)<60" class="card  card-danger text-center my-3">
                  <div class="card-body">
                      <h2>Nice! You passed this quiz!</h2>
                      <h3 v-text="(passedQuiz[0].note/numb*100)+'%'"></h3>
                      <i class="mdi mdi-close-thick mdi-48px" style="color: #ff0404"></i>
                  </div>
              </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Course",
  data() {
    return {
      currentCourse:null,
      currentChapter: null,
      currentSubChapter: null,
      currentQuiz: null,
      passedQuiz:null,
      isPassed:false,
      answer:[],
      checkedAnswer:[],
      numb:0,
      active_el: 1,
    };
  },
  created() {
    this.getCurrentCourse();
  },
  methods: {
    activateEl(el) {
      this.active_el = el;
    },
    getAnswer(event,questionId,questionOptionId){
      alert(event.target.value)
      this.answer[questionId] = event.target.value !== 'false';
      this.checkedAnswer[Number(questionId)]=Number(questionOptionId);
    },
    getCurrentCourse() {
      alert("current course")
      this.currentCourse = this.$store.state.auth.userState.user.courses.filter(obj => {return obj.id === Number(this.$route.params.courseId);}
      );
    },
    getNextChapter() {
      if (this.currentCourse[0].chapters.indexOf(this.currentChapter) < this.currentCourse[0].chapters.length - 1) {
        this.currentChapter = this.currentCourse[0].chapters[this.currentCourse[0].chapters.indexOf(this.currentChapter) + 1];
      }
    },
    getPreviousChapter() {
      if (this.currentCourse[0].chapters.indexOf(this.currentChapter) > 0) {
        this.currentChapter = this.currentCourse[0].chapters[this.currentCourse[0].chapters.indexOf(this.currentChapter) - 1];
      }
    },
    getCurrentChapter(chapterId) {
      this.currentChapter = this.currentCourse.chapters[chapterId];
    },
    getCurrentSubChapter(subChapterId) {
      this.currentSubChapter = this.currentChapter.subChapters[subChapterId];
    },
    getNextSubChapter() {
      if (this.currentChapter.subChapters.indexOf(this.currentSubChapter)<(this.currentChapter.subChapters.length-1))
      this.currentSubChapter = this.currentChapter.subChapters[this.currentChapter.subChapters.indexOf(this.currentSubChapter) + 1];
      else
        if (this.currentCourse[0].chapters.indexOf(this.currentChapter) < this.currentCourse[0].chapters.length - 1){
          this.getNextChapter()
        }
    },
    getPreviousSubChapter() {
      if (this.currentChapter.subChapters.indexOf(this.currentSubChapter)>0)
      this.currentSubChapter = this.currentChapter.subChapters[this.currentChapter.subChapters.indexOf(this.currentSubChapter) - 1];
      else if (this.currentCourse[0].chapters.indexOf(this.currentChapter) > 0){
        this.getPreviousChapter()
      }
    },
    getCurrentQuiz(quizId){
        this.numb=0;
      this.currentQuiz=this.currentCourse[0].quizzes[quizId];
      this.currentQuiz.questions.forEach(question=>{
        this.numb+=question.mark;});
      if (this.$store.state.auth.userState.user.passedQuizs.length>0){
          this.passedQuiz=this.$store.state.auth.userState.user.passedQuizs.filter(obj=>{return obj.quiz.id===Number(this.currentQuiz.id)});
      }
      this.isPassed = this.passedQuiz.length > 0;
    },
    submitQuiz(){
      let  note=0;
      for (let i=0;i<this.answer.length;i++){
        if (this.answer[i])
          note+=this.currentQuiz.questions[i].mark;
      }
      this.submitTest(note)
    },
    submitTest(note){
      let passedQuiz=new  FormData();
      passedQuiz.append('note',note);
      passedQuiz.append('personId',this.$store.state.auth.userState.user.id);
      passedQuiz.append('quizId',this.currentQuiz.id)
      this.$store.dispatch("formation/createPassedQuiz",passedQuiz).then(this.$router.push(`/`));
    },
  },
  watch: {
    currentCourse: function() {
      this.currentChapter = this.currentCourse[0].chapters[0];
    },
    currentChapter: function() {
      this.currentSubChapter = this.currentChapter.subChapters[0];
    }
  }
};
</script>

<style scoped>
body {
  background-color: #ecebed;
}
.container {
  width: 70%;
}
.video {
  border-radius: 2px;
  width: 92%;
  height: 460px;
}
.subBtn {
  width: 100px;
  height: 12px;
}
.btn-nav:hover {
  background-color: #21c4ed;
  color: #fff;
}
.btn-nav {
  background-color: #c5c8c8;
}
  .card{
    background-color: #c7dcdb;
  }
  .card-title{
    border-bottom: #0a0c0c 2px solid;
  }
    .card-success{
        width: 50%;
        background-color: #ebe9ee;
        border: #149c09 3px solid;
        box-shadow:3px 5px 5px #565656;
        border-radius: 10px;
    }
.card-danger{
    width: 50%;
    background-color: #ebe9ee;
    border: #ff0404 3px solid;
    box-shadow:3px 5px 5px #565656;
    border-radius: 10px;
}
    body{
        background-color: #EEE;
    }
</style>
