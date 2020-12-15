<template>
  <div class="global-card">
    <div class="card d-inline-block  my-2">
      <img :src="image" class="card-img-top" alt="..." />
      <div class="card-body">
        <h5 class="card-title my-2" style="height:50px;overflow: hidden;color: #efb932">{{ CardCourse.title }}</h5>
        <p class="card-text" style="color: #e4fffc">{{ CardCourse.description }}</p>
        <a
          href="#"
          class="btn btn-primary"
          data-toggle="modal"
          :data-target="'#course'+CardCourse.id"
          @click="getCourse"
          >More...</a
        >
      </div>
    </div>
    <!-- Modal -->
    <div
      class="modal fade"
      :id="'course'+CardCourse.id"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalCenterTitle"
      aria-hidden="true"
    >
      <div class="modal-dialog  modal-dialog-scrollable modal-xl" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title " id="exampleModalLongTitle">
              Course:{{ CardCourse.title }}
            </h5>
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
            <div class="row">
                <div v-if="!$store.state.auth.userState.loggedIn" class="col-12">
                    <button @click="addPersonToCourse($event)" class="btn btn-enroll mx-3 rounded-0 px-4 py-2" data-dismiss="modal">Enroll</button>
                </div>
                    <div v-else-if="$store.state.auth.userState.user.roles[0].roleName==='ROLE_STUDENT'" class="col-12">
                    <router-link v-if="Enrolled.length>0" tag="button" class="btn btn-go" :to="{name:'Course',params:{courseId:CardCourse.id}}" data-dismiss="modal">Go to course</router-link>
                        <button v-else @click="addPersonToCourse($event)" class="btn btn-enroll mx-3 rounded-0 px-4 py-2" data-dismiss="modal">Enroll</button>
                    </div>

              <div class="col-12 px-5">
                <h1 class="my-3">About this Course</h1>
                <p class="text-justify">{{CardCourse.description }}</p>
              </div>
              <div class="col-12 px-5">
                <div class="row">
                  <div class="col-12 text-center">
                    <h1>Syllabus - What you will learn from this course</h1>
                  </div>
                  <div class="col-12" v-for="(chapter,chapterIndex) in CardCourse.chapters" :key="chapter.id">
                    <h3 class="rounded-pill my-2 px-4 py-1 chapter"  data-toggle="collapse" :href="'#chapter'+chapter.id" role="button" aria-expanded="false" :aria-controls="chapter.title.split(' ').join('_')">
                      CHAPTER.{{chapterIndex+1}}<i class="mdi mdi-send mdi-24px mx-3" style="color:#ffc128;"></i>{{chapter.title}}
                    </h3>
                    <div class="collapse" :id="'chapter'+chapter.id">
                      <div class="card card-body border-0 description mx-5">{{chapter.description}}</div>
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
</template>

<script>
export default {
  name: "CardCourse",
  props: {
    CardCourse : {
      type: Object
    },
  },
 data(){
    return{
      image:require(`../assets/videos/${this.CardCourse.courseResource.fileName}`),
      Enrolled:[]
    }
 },
  methods:{
    addPersonToCourse(event){
      alert(this.CardCourse.id)
        if (this.$store.state.auth.userState.loggedIn){
      let addCourse=new  FormData();
      addCourse.append('courseId',this.CardCourse.id)
      addCourse.append('personId',this.$store.state.auth.userState.user.id)
      this.$store.dispatch("formation/addPersonToCourse",addCourse);
      this.$router.push({name:'Course',params:{courseId:this.CardCourse.id}})
        }else
            this.$router.push(`/login/${true}`);
        event.preventDefault()
    },
    getCourse(){
      this.Enrolled=this.$store.state.auth.userState.user.courses.filter(obj=>{return obj.id===this.CardCourse.id});
    }
  },
  created() {
    this.$store.dispatch("auth/getPerson");
  }
};
</script>

<style scoped>
  .modal-header{
    background: #24b0c2;
  }
  .card{
    background-color: #333a53;
    border-radius: 3%;
    border:0;
  }
  .card:hover{
    border: 3px solid #efb932;
  }
  .card-img-top{
    height: 200px;
    border-top-left-radius: 3%;
    border-top-right-radius: 3%;
  }
  .card-text{
    height: 95px;
    overflow: hidden;
    text-align: justify;
  }
  .modal-body{
    font-family: "Fira Code",serif;
    font-size: 20px;
  }
  .chapter{
    background: #4ca1af;
    background: linear-gradient(to right, #4ca1af, #c4e0e5);
  }
  .description{
    background-color: #c0d0d2;
    height: 150px;
    border-radius: 2%;
  }
  .btn-go{
    background-color: #eebc10;
    color: #000;
    font-weight: bold;
  }
  .btn-enroll{
    background:none;
    border: 2px solid #eebc10;
    font-weight: bold;
  }
  .btn-enroll:hover{
    background-color:#eebc10;
  }
</style>
