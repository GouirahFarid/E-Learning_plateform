import FormationService from '../services/formation.service';
export const formation={
    namespaced: true,
    state:{
        categories:[],
        message:''
    },
    getters:{
        getCourseByCategotyAndId:(state)=>(categoryId,courseId)=>{
            return state.categories[Number(categoryId)].courses[Number(courseId)]
        }
    },
    mutations:{
        GET_CATEGORIES(state,value){
            state.categories.push(value)
        },
        CREATE_CATEGORY(state,value){
            state.message=value;
        },
        UPDATE_CATEGORY(state,value){
            state.message=value;
        },
        CREATE_COURSE(state,value){
            state.message=value;
        },
        UPDATE_COURSE(state,value){
            state.message=value;
        },
        CREATE_CHAPTER(state,value){
            state.message=value;
        },
        UPDATE_CHAPTER(state,value){
            state.message=value;
        },
        CREATE_SUB_CHAPTER(state,value){
            state.message=value;
        },
        UPDATE_SUB_CHAPTER(state,value){
            state.message=value;
        },
        CREATE_QUIZ(state,value){
            state.message=value;
        },
        UPDATE_QUIZ(state,value){
            state.message=value;
        },
        CREATE_QUESTION(state,value){
            state.message=value;
        },
        UPDATE_QUESTION(state,value){
            state.message=value;
        },
        CREATE_QUESTION_OPTION(state,value){
            state.message=value;
        },
        UPDATE_QUESTION_OPTION(state,value){
            state.message=value;
        },
        DELETE_CATEGORY(state,value){
            state.message=value;
        },
        DELETE_COURSE(state,value){
            state.message=value;
        },
        DELETE_CHAPTER(state,value){
            state.message=value;
        },
        DELETE_SUB_CHAPTER(state,value){
            state.message=value;
        },
        DELETE_QUIZ(state,value){
            state.message=value;
        },
        DELETE_QUESTION(state,value){
            state.message=value;
        },
        DELETE_QUESTION_OPTION(state,value){
            state.message=value;
        },
        ADD_PERSON_TO_COURSE(state,value){
            state.message=value;
        },
        REMOVE_PERSON_FROM_COURSE(state,value){
            state.message=value;
        },
        PASSED_QUIZ(state,value){
            state.message=value;
        },
        CHANGE_CATEGORIES(state){
            state.categories=[]
        }
    },
    actions:{
        getCategories({commit}){
            let cats=FormationService.getCategories();
           commit('CHANGE_CATEGORIES')
            cats.then(function (v) {
               v.forEach(value=>commit('GET_CATEGORIES',value));
            });
        },
        createCategory({commit},category){
            const msg=FormationService.createCategory(category);
            msg.then(value => {commit('CREATE_CATEGORY',value)});
        },
        updateCategory({commit},category){
            const msg=FormationService.updateCategory(category);
            msg.then(value => {commit('UPDATE_CATEGORY',value)});
        },
        createCourse({commit},category){
            const msg=FormationService.createCourse(category);
            msg.then(value => {commit('CREATE_COURSE',value)});
        },
        updateCourse({commit},category){
            const msg=FormationService.updateCourse(category);
            msg.then(value => {commit('UPDATE_COURSE',value)});
        },
        createChapter({commit},chapter){
            const msg=FormationService.createChapter(chapter);
            msg.then(value => {commit('CREATE_CHAPTER',value)});
        },
        updateChapter({commit},chapter){
            const msg=FormationService.updateChapter(chapter);
            msg.then(value => {commit('UPDATE_CHAPTER',value)});
        },
        createSubChapter({commit},subChapter){
            const msg=FormationService.createSubChapter(subChapter);
            msg.then(value => {commit('CREATE_SUB_CHAPTER',value)});
        },
        updateSubChapter({commit},subChapter){
            const msg=FormationService.updateSubChapter(subChapter);
            msg.then(value => {commit('UPDATE_SUB_CHAPTER',value)});
        },
        createQuiz({commit},quiz){
            const msg=FormationService.createQuiz(quiz);
            msg.then(value => {commit('CREATE_QUIZ',value)});
        },
        updateQuiz({commit},quiz){
            const msg=FormationService.updateQuiz(quiz);
            msg.then(value => {commit('UPDATE_QUIZ',value)});
        },
        createQuestion({commit},Question){
            const msg=FormationService.createQuestion(Question);
            msg.then(value => {commit('CREATE_QUESTION',value)});
        },
        updateQuestion({commit},Question){
            const msg=FormationService.updateQuestion(Question);
            msg.then(value => {commit('UPDATE_QUESTION',value)});
        },
        createQuestionOption({commit},QuestionOption){
            const msg=FormationService.createQuestionOption(QuestionOption);
            msg.then(value => {commit('CREATE_QUESTION_OPTION',value)});
        },
        updateQuestionOption({commit},QuestionOption){
            const msg=FormationService.updateQuestionOption(QuestionOption);
            msg.then(value => {commit('UPDATE_QUESTION_OPTION',value)});
        },
        deleteCategory({commit},category){
            const msg=FormationService.deleteCategory(category);
            msg.then(value => {commit('DELETE_CATEGORY',value)});
        },
        deleteCourse({commit},course){
            const msg=FormationService.deleteCourse(course);
            msg.then(value => {commit('DELETE_COURSE',value)});
        },
        deleteChapter({commit},chapter){
            const msg=FormationService.deleteChapter(chapter);
            msg.then(value => {commit('DELETE_CHAPTER',value)});
        },
        deleteSubChapter({commit},subChapter){
            const msg=FormationService.deleteSubChapter(subChapter);
            msg.then(value => {commit('DELETE_SUB_CHAPTER',value)});
        },
        deleteQuiz({commit},quiz){
            const msg=FormationService.deleteQuiz(quiz);
            msg.then(value => {commit('DELETE_QUIZ',value)});
        },
        deleteQuestion({commit},Question){
            const msg=FormationService.deleteQuestion(Question);
            msg.then(value => {commit('DELETE_QUESTION',value)});
        },
        deleteQuestionOption({commit},QuestionOption){
            const msg=FormationService.deleteQuestionOption(QuestionOption);
            msg.then(value => {commit('DELETE_QUESTION_OPTION',value)});
        },
        addPersonToCourse({commit},addCourse){
            const msg=FormationService.addPersonToCourse(addCourse);
            msg.then(value => {commit('ADD_PERSON_TO_COURSE',value)});
        },
        removePersonFromCourse({commit},course){
            const msg=FormationService.removePersonFromCourse(course);
            msg.then(value => {
                alert(value)
                commit('REMOVE_PERSON_FROM_COURSE',value)});
        },
        createPassedQuiz({commit},passedQuiz){
            const msg=FormationService.createPassedQuiz(passedQuiz);
            msg.then(value => {commit('PASSED_QUIZ',value)});
        },
    }

};