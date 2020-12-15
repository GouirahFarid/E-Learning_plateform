import axios from "axios";
const API_URL = "http://localhost:9001/api/";
import authHeader from "./auth-header";
class FormationService {
    /**************************************CATEGORY******************************************/
    async getCategories(){
       return await axios.get(API_URL+'categories').then(response => {return Promise.resolve(response.data)});
    };
    async createCategory(category){
        return  await  axios.post(API_URL+'categories',category,{headers:{'Authorization':authHeader()}}).then(response=>{return Promise.resolve(response.data)});
    };
    async updateCategory(category){
        return  await  axios.patch(API_URL+`categories`,category,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)});
    }
    async deleteCategory(category){
        return  await  axios.post(API_URL+`categories/delete`,category,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)});
    }
    /*********************************************COURSE**************************************/
    async createCourse(course){
        return  await  axios.post(API_URL+`courses`,course,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    async updateCourse(course){
        return  await  axios.patch(API_URL+`courses`,course,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)});
    }
    async deleteCourse(course){
        return  await  axios.post(API_URL+`courses/delete`,course,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)});
    }
    async addPersonToCourse(addCourse){
        return  await  axios.post(API_URL+`persons/add`,addCourse,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    async removePersonFromCourse(course){
        return  await  axios.post(API_URL+`persons/remove`,course,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    /**************************************Chapter************************************************/
    async createChapter(chapter){
        return  await  axios.post(API_URL+`chapters`,chapter,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)});
    }
    async updateChapter(chapter){
        return  await  axios.patch(API_URL+`chapters`,chapter,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)});
    }
    async deleteChapter(chapter){
        return  await  axios.post(API_URL+`chapters/delete`,chapter,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)});
    }
    /**************************************SubChapters***********************************************/
    async createSubChapter(subChapter){
        return  await  axios.post(API_URL+`subChapters`, subChapter,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    async updateSubChapter(subChapter){
        return  await  axios.patch(API_URL+`subChapters`,subChapter,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    async deleteSubChapter(subChapter){
        return  await  axios.post(API_URL+`subChapters/delete`,subChapter,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    /**************************************Quiz***********************************************/
    async createQuiz(quiz){
        return  await  axios.post(API_URL+`quizzes`, quiz,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    async updateQuiz(quiz){
        return  await  axios.patch(API_URL+`quizzes`,quiz,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    async deleteQuiz(quiz){
        return  await  axios.post(API_URL+`quizzes/delete`,quiz,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    /**************************************question***********************************************/
    async createQuestion(question){
        return  await  axios.post(API_URL+`questions`, question,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    async updateQuestion(question){
        return  await  axios.patch(API_URL+`questions`,question,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    async deleteQuestion(question){
        return  await  axios.post(API_URL+`questions/delete`,question,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    /**************************************question option***********************************************/
    async createQuestionOption(questionOption){
        return  await  axios.post(API_URL+`questionOptions`, questionOption,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    async updateQuestionOption(questionOption){
        return  await  axios.patch(API_URL+`questionOptions`,questionOption,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    async deleteQuestionOption(questionOption){
        return  await  axios.post(API_URL+`questionOptions/delete`,questionOption,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    /*******************************************************Quiz****************************/
    async createPassedQuiz(passedQuiz){
        return  await  axios.post(API_URL+`passed`,passedQuiz,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }





}
export default new FormationService();