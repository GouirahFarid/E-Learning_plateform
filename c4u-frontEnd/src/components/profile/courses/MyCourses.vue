<template>
  <div class="m-0 p-0">
    <div class="row">
      <div class="col-12 text-center"><h2>My Courses</h2></div>
      <div class="col-12 text-center" v-if="this.$store.state.auth.userState.user.roles[0].roleName!=='ROLE_STUDENT'">
        <button style="background-color: #ffc128;" @click="changeCourse(true);getCurrentForm('course')" class="btn btn-block border-0" data-toggle="modal"
                data-target="#editCourseModel">Create new course</button>
      </div>
      <div v-if="hasCourses" class="col-12 mt-2">
        <div class="table-responsive">
          <table class="table table-bordered table-hover">
            <thead>
              <tr>
                <th>#</th>
                <th>Courses</th>
                <th>Control</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(course, courseIndex) in courses" :key="course.id">
                <td>{{ courseIndex+1}}</td>
                <td>{{ course.title }}</td>
                <td  v-if="$store.state.auth.userState.user.roles[0].roleName==='ROLE_ADMIN' ||$store.state.auth.userState.user.roles[0].roleName==='ROLE_PROFESSOR' " class="text-center">
                  <button @click="getQuizzes(true); getCurrentCourse(courseIndex);" class="btn btn-outline-success">Quizzes</button>
                  <button
                    class="btn btn-primary mx-2"
                    data-toggle="modal"
                    data-target="#courseModel"
                    @click="getCurrentCourse(courseIndex)"
                  >
                    More...
                  </button>
                  <button @click="changeCourse(false) ; getCurrentCourse(courseIndex);getCurrentForm('course')" class="btn btn-success mx-2" data-toggle="modal"
                          data-target="#editCourseModel">
                    <i class="mdi mdi-pencil-plus-outline h5"></i>
                  </button>
                  <button @click="deleteCourse(course.id)" class="btn btn-outline-danger mx-2">
                    <i class="mdi mdi-delete h5"></i>
                  </button>
                </td>
                <td v-else>
                  <router-link  tag="button" class="btn btn-go" :to="{name:'Course',params:{courseId:course.id}}">Go to course</router-link>
                  <button @click="removePersonFromCourse(course.id)" class="btn btn-primary">Unroll</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div v-if="hasCourses"  class="col-12 text-center mt-3">
        <i @click="previousCourses" class="mdi mdi-chevron-left-circle mdi-36px  text-info mx-5"></i>
        <i @click="nextCourses" class="mdi mdi-chevron-right-circle mdi-36px text-info mx-5"></i>
      </div>
      <div v-else class="col-12 text-center mt-3 alert alert-danger"><h2>You don't have any course</h2></div>
      <div class="col-12 quiz my-5">
        <CourseQuizzes v-if="isQuizzes" :course-quizzes="currentCourse"></CourseQuizzes>
      </div>
    </div>
    <!-- course Modal-->
    <div v-if="currentCourse" class="modal fade" id="courseModel" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog"
         aria-labelledby="staticBackdropLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog  modal-dialog-scrollable modal-xl">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class="col-12 my-3">

                    <button @click="changeChapter(true);getCurrentForm('chapter')" class="btn btn-outline-secondary mx-2"
                            data-toggle="modal"
                            data-target="#editCourseModel"
                    >Create chapter</button>
              </div>
              <div class="col-12" v-for="(chapter,chapterIndex) in currentCourse.chapters" :key="chapter.id">
                <div class="row chapter my-2 py-2 rounded-pill">
                  <div class="col-9"
                       data-toggle="collapse" :href="'#chapter'+chapterIndex"
                       role="button" aria-expanded="false" :aria-controls="'chapter'+chapterIndex"
                  ><h2 class="btn">CH.{{chapterIndex+1}}<i class="mdi mdi-send mdi-24px mx-3" style="color:#ffc128;"></i> {{chapter.title}}</h2></div>
                  <div class="col-3 text-left">
                    <button class="btn btn-primary"
                            data-toggle="modal"
                            data-target="#editCourseModel"
                            @click="changeSubChapter(true);getCurrentForm('subChapter');getCurrentChapter(chapterIndex)"
                            data-dismiss="modal"
                    ><i class="mdi mdi-plus-thick"></i> </button>
                    <button @click="getCurrentChapter(chapterIndex); getCurrentForm('chapter');changeChapter(false)"
                            class="btn btn-outline-primary mx-1"
                            data-toggle="modal"
                            data-dismiss="modal"
                            data-target="#editCourseModel"
                    ><i class="mdi mdi-pencil-plus mx-2"></i> </button>
                    <button @click="deleteChapter(chapter.id)" class="btn btn-outline-danger" data-dismiss="modal"><i class="mdi mdi-delete"></i></button>
                  </div>
                </div>
                <div class="collapse" :id="'chapter'+chapterIndex">
                  <div class="card card-body rounded-0 border-0">
                    <div class="row">
                      <div class="col-12  py-2 my-1 subChapter rounded-pill" v-for="(subChapter ,subChapterIndex) in chapter.subChapters" :key="subChapter.id">
                        <div class="row">
                          <div class="col-9 pl-5">CH.{{subChapterIndex+1}}<i class="mdi mdi-send mdi-24px mx-3" style="color:#ffc128;"></i>{{subChapter.title}}</div>
                          <div class="col-3">
                            <button @click="deleteSubChapter(subChapter.id)" class="btn btn-outline-danger mx-2" data-dismiss="modal"><i class="mdi mdi-delete"></i> </button>
                            <button @click="getCurrentSubChapter(chapterIndex,subChapterIndex);
                                            getCurrentForm('subChapter');
                                            changeSubChapter(false)"
                                    data-dismiss="modal"
                                    class="btn btn-outline-primary mx-1"
                                    data-toggle="modal"
                                    data-target="#editCourseModel">
                              <i class="mdi mdi-pencil-plus-outline h5"></i>
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
    <!-- Course Modal -->
<!--       Edit course Modal -->
      <div
              class="modal fade"
              id="editCourseModel"
              data-backdrop="static"
              data-keyboard="false"
              tabindex="-1"
              role="dialog"
              aria-labelledby="staticBackdropLabel"
              aria-hidden="true"
      >
          <div class="modal-dialog  modal-dialog-scrollable modal-lg">
              <div class="modal-content">
                  <div class="modal-header">
                      <button
                              type="button"
                              class="close"
                              data-dismiss="modal"
                              aria-label="Close"
                      >
                          <span aria-hidden="true">&times;</span>
                      </button>
                  </div>
                  <div class="modal-body">
                    <NewCourseForm v-if="isNewCourse && currentForm==='course'"></NewCourseForm>
                    <UpdateCourseForm v-if="!isNewCourse && currentForm==='course' && currentCourse" :current-course="currentCourse"></UpdateCourseForm>
                    <NewChapterForm v-if="isNewChapter && currentForm==='chapter'" :course-id="currentCourse.id"></NewChapterForm>
                    <UpdateChapterForm v-if="!isNewChapter &&currentForm==='chapter' &&currentChapter" :current-chapter="currentChapter"></UpdateChapterForm>
                    <NewSubChapterForm v-if="currentForm==='subChapter' && isNewSubChapter" :current-chapter="currentChapter"></NewSubChapterForm>
                    <UpdateSubChapterForm :current-sub-chapter="currentSubChapter" v-if="currentForm==='subChapter' && !isNewSubChapter" ></UpdateSubChapterForm>
                  </div>
              </div>
          </div>
      </div>
<!--       Edit Course Modal -->
  </div>
</template>

<script>
import NewChapterForm from "./chapter/NewChapterForm";
import UpdateChapterForm from "./chapter/UpdateChapterForm"
import  NewCourseForm from "../courses/course/NewCourseForm"
import  UpdateCourseForm from "../courses/course/UpdateCourseForm"
import  NewSubChapterForm from "./subChapter/NewSubChapterForm"
import  UpdateSubChapterForm from "./subChapter/UpdateSubChapterForm"
import CourseQuizzes from "./course/CourseQuizzes"
export default {
  name: "Courses",
  data() {
    return {
      isNewChapter:'',
      isNewCourse:'',
      currentCourse:'',
      currentChapter:'',
      currentSubChapter:'',
      currentForm:'course',
      isNewSubChapter:'',
      isQuizzes:'',
      step:5,
      currentIndex:0,
      courses:this.$store.state.auth.userState.courses
    };
  },
  components: {
    NewChapterForm,
    UpdateChapterForm,
    NewCourseForm,
    UpdateCourseForm,
    NewSubChapterForm,
    UpdateSubChapterForm,
    CourseQuizzes
  },
  computed:{
    hasCourses:function(){
      return this.courses.length>0
    }
  },
  methods: {
    changeChapter(value) {
      this.isNewChapter = value;
    },
    changeCourse(value) {
      this.isNewCourse = value;
    },
    changeSubChapter(value){
      this.isNewSubChapter=value
    },
    getCurrentCourse(courseId) {
      this.currentCourse = this.courses[courseId];
    },
    getCurrentForm(value){
      this.currentForm=value;
    },
    getCurrentChapter(value){
      this.currentChapter=this.currentCourse.chapters[value];
    },
    getCurrentSubChapter(chapterId,subChapterId){
      this.currentSubChapter=this.currentCourse.chapters[chapterId].subChapters[subChapterId];
    },
    getQuizzes(value){
      this.isQuizzes=value;
    },
    deleteCourse(courseId){
      let  course=new  FormData();
      course.append('courseId',courseId)
      this.$confirm("Are you sure?").then(() => {
        this.$store.dispatch("formation/deleteCourse", course);
        this.$router.push({name:'Profile',params:{type:this.$store.state.auth.userState.user.roles[0].roleName,account:"account"}})
      })
    },
    deleteChapter(chapterId){
      let  chapter=new  FormData();
      chapter.append('chapterId',chapterId)
      this.$confirm("Are you sure?").then(() => {
        this.$store.dispatch("formation/deleteChapter", chapterId)
        this.$router.push({name:'Profile',params:{type:this.$store.state.auth.userState.user.roles[0].roleName,account:'account'}});
      });
    },
    deleteSubChapter(subChapterId) {
      let subChapter = new FormData();
      subChapter.append('subChapterId', subChapterId)
      this.$confirm("Are you sure?").then(() => {
        this.$store.dispatch("formation/deleteSubChapter", subChapterId)
        this.$router.push({name:'Profile',params:{type:this.$store.state.auth.userState.user.roles[0].roleName,account:'account'}});
      });
    },
    removePersonFromCourse(courseId){
      let course=new  FormData();
      course.append('courseId',courseId)
      course.append('personId',this.$store.state.auth.userState.user.id)
      this.$confirm("Are you sure?").then(() => {
        this.$store.dispatch("formation/removePersonFromCourse",course)
        this.$router.push({name:'Profile',params:{type:this.$store.state.auth.userState.user.roles[0].roleName,account:'account'}});
      })
    },
    nextCourses(){
      if(this.currentIndex+this.step<(this.courses.length-1)){
        this.currentIndex+=this.step;
        this.step+=this.step
      }
    },
    previousCourses(){
      if (this.step-this.currentIndex>0){
        this.step-=this.currentIndex;
        this.currentIndex-=this.step;
      }
    }

  },
  created() {
    this.$store.dispatch('auth/getPerson')
    this.courses=this.$store.state.auth.userState.user.courses;
  }
};
</script>

<style scoped>
thead {
  background-color: #301b68;
  color: #edffff;
}
  .modal-header{
    background: #24b0c2;
  }
  .chapter{
    background: #4ca1af;
    background: linear-gradient(to right, #4ca1af, #c4e0e5);

  }
  .subChapter{
    background: #00c6ff;
    background: linear-gradient(to right, #0072ff, #00c6ff);

  }
.btn-go{
  background-color: #eebc10;
  color: #000;
  font-weight: bold;
}
</style>
