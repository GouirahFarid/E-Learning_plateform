<template>
        <div class="row justify-content-center">
            <div class="col-12 my-5 admins">
                <div class="row">
                    <div class="col-12 my-1 text-center"><h1>ADMINS</h1></div>
                    <div class="col-12 my-1">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td class="text-center">#</td>
                                    <td >Username</td>
                                    <td class="text-center">register date</td>
                                    <td class="text-center">number of courses</td>
                                    <td class="text-center">Control</td>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="(user,userIndex) in admins" :key="user.id">
                                    <td class="text-center">{{userIndex+1}}</td>
                                    <td class="text-center">{{user.username}}</td>
                                    <td class="text-center">{{user.createDate}}</td>
                                    <td class="text-center">{{user.courses.length}}</td>
                                    <td class="text-center"><button @click="deletePerson(user.id)" class="btn btn-danger">Delete</button> </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-12 text-center ">
                        <i @click="previousAdmins" class="mdi mdi-chevron-left-circle mdi-36px  text-info mx-5"></i>
                        <i @click="nextAdmins" class="mdi mdi-chevron-right-circle mdi-36px text-info mx-5"></i>
                    </div>
                </div>
            </div>
            <div class="col-12 professors">
                <div class="row">
                    <div class="col-12 my-1 text-center"><h1>Professors</h1></div>
                    <div class="col-12 mt-1">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td class="text-center">#</td>
                                    <td >Username</td>
                                    <td class="text-center">register date</td>
                                    <td class="text-center">number of courses</td>
                                    <td class="text-center">Control</td>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="(user,userIndex) in professors.slice(professorsIndex,professorsIndex+stepProfessors)" :key="user.id">
                                    <td class="text-center">{{userIndex+1}}</td>
                                    <td class="text-center">{{user.username}}</td>
                                    <td class="text-center">{{user.createDate}}</td>
                                    <td class="text-center">{{user.courses.length}}</td>
                                    <td class="text-center"><button @click="deletePerson(user.id)" class="btn btn-danger">Delete</button> </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-12 text-center ">
                        <i @click="previousProfessors"  class="mdi mdi-chevron-left-circle mdi-36px  text-info mx-5"></i>
                        <i @click="nextProfessors" class="mdi mdi-chevron-right-circle mdi-36px text-info mx-5"></i>
                    </div>
                </div>
            </div>
            <div class="col-12 my-5 students">
                <div class="row">
                    <div class="col-12 my-1 text-center"><h1>STUDENTS</h1></div>
                    <div class="col-12 my-1">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <td class="text-center">#</td>
                                    <td >Username</td>
                                    <td class="text-center">register date</td>
                                    <td class="text-center">number of courses</td>
                                    <td class="text-center">Control</td>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="(user,userIndex) in students" :key="user.id">
                                    <td class="text-center">{{userIndex+1}}</td>
                                    <td class="text-center">{{user.username}}</td>
                                    <td class="text-center">{{user.createDate}}</td>
                                    <td class="text-center">{{user.courses.length}}</td>
                                    <td class="text-center"><button @click="deletePerson(user.id)" class="btn btn-danger">Delete</button> </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-12 text-center mt-3">
                        <i @click="previousStudents" class="mdi mdi-chevron-left-circle mdi-36px  text-info mx-5"></i>
                        <i @click="nextStudents" class="mdi mdi-chevron-right-circle mdi-36px text-info mx-5"></i>
                    </div>
                </div>
            </div>

        </div>
</template>

<script>
    export default {
        name: "Users",
        data(){
            return{
                admins:this.$store.state.auth.userState.admins,
                professors:this.$store.state.auth.userState.professors,
                students:this.$store.state.auth.userState.students,
                stepAdmins:3,
                adminsIndex:0,
                stepProfessors:3,
                professorsIndex:0,
                stepStudents:3,
                studentsIndex:0,
            }
        },
        methods:{
            deletePerson(personId){
                let  person=new  FormData();
                person.append('personId',personId)
                this.$store.dispatch("auth/deletePerson", person).then(() => {
                    alert(personId)
                    this.$router.push("/")});

            },
            nextAdmins(){
                if(this.adminsIndex+this.stepAdmins<(this.admins.length-1)){
                    this.adminsIndex+=this.stepAdmins;
                    this.stepAdmins+=this.stepAdmins
                }
            },
            previousAdmins(){
                if (this.stepAdmins-this.adminsIndex>0){
                    this.stepAdmins-=this.adminsIndex;
                    this.adminsIndex-=this.stepAdmins;
                }
            },
            nextProfessors(){
                if(this.professorsIndex+this.stepProfessors<(this.professors.length-1)){
                    this.professorsIndex+=this.stepProfessors;
                    this.stepProfessors+=this.stepProfessors
                }
            },
            previousProfessors(){
                if (this.stepProfessors-this.professorsIndex>0){
                    this.stepProfessors-=this.professorsIndex;
                    this.professorsIndex-=this.stepProfessors;
                }
            },
            nextStudents(){
                if(this.studentsIndex+this.stepStudents<(this.students.length-1)){
                    this.studentsIndex+=this.stepStudents;
                    this.stepStudents+=this.stepStudents
                }
            },
            previousStudents(){
                if (this.stepStudents-this.studentsIndex>0){
                    this.stepStudents-=this.studentsIndex;
                    this.studentsIndex-=this.stepStudents;
                }
            }
        }
    }
</script>

<style scoped>
.row{width: 100%;
}
thead {
    background-color: #301b68;
    color: #edffff;
}
</style>