
import AuthService from '../services/auth.service';
const localUser = JSON.parse(localStorage.getItem('user'));
// const initialState = user?{ status: { loggedIn: true }, user } : { status: { loggedIn: false }, user: null };
export const auth = {
    namespaced: true,
    state: {
        userState:{
                loggedIn:!!localUser,
                user:status,
                admins:[],
                professors:[],
                students:[],
                message:null

        }
    },
    actions: {
        login({ commit }, user) {
            return AuthService.login(user).then(
                user => {
                    commit('loginSuccess', user);
                    return Promise.resolve(user);
                },
                error => {
                    commit('loginFailure');
                    return Promise.reject(error);
                }
            );
        },
        logout({ commit }) {
            AuthService.logout();
            commit('logout');
        },
        register({ commit }, user) {
            return AuthService.register(user).then(
                message => {
                    commit('registerSuccess');
                    return Promise.resolve(message);
                },
                error => {
                    commit('registerFailure');
                    return Promise.reject(error);
                }
            );
        },
        getPerson({commit}){
            const  person=AuthService.getUser();
            person.then(value => {
                commit('GET_USER',value)
            });
        },
        getStudents({commit}){
            const students=AuthService.getPersonsByRoleName('ROLE_STUDENT');
            students.then(students=>{students.forEach(student=>{commit('GET_STUDENTS',student)})})
        },
        getAdmins({commit}){
            const admins=AuthService.getPersonsByRoleName('ROLE_ADMIN');
            admins.then(admins=>{admins.forEach(admin=>{commit('GET_ADMINS',admin)})})
        },
        getProfessors({commit}){
            const professors=AuthService.getPersonsByRoleName('ROLE_PROFESSOR');
            professors.then(professors=>{professors.forEach(professor=>{commit('GET_PROFESSORS',professor)})})
        },
        updatePerson({commit},person){
            const msg=AuthService.updatePerson(person);
            msg.then(value => {commit('UPDATE_PERSON',value)});
        },
        deletePerson({commit},person){
            const msg=AuthService.deletePerson(person);
            msg.then(value => {commit('DELETE_PERSON',value)});
        },
    },
    mutations: {
        loginSuccess(state, user) {
            state.userState.loggedIn = true;
            state.userState.user = user;
        },
        loginFailure(state) {
            state.userState.loggedIn = false;
            state.userState.user = null;
        },
        logout(state) {
            state.userState.loggedIn = false;
            state.userState.user = null;
        },
        registerSuccess(state) {
            state.userState.loggedIn = false;
        },
        registerFailure(state) {
            state.userState.loggedIn = false;
        },
        GET_USER(state,user){
            state.userState.user=user
        },
        GET_PROFESSORS(state,professor){
            state.userState.professors.push(professor)
        },
        GET_STUDENTS(state,student){
            state.userState.students.push(student)
        },
        GET_ADMINS(state,admin){
            state.userState.admins.push(admin)
        },
        UPDATE_PERSON(state,value){
            state.userState.message=value;
        },
        DELETE_PERSON(state,value){
            state.userState.message=value;
        }
    }
};