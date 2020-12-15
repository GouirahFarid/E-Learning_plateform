import axios from "axios";
import authHeader from "./auth-header";
const API_URL = "http://localhost:9001/api/";
const localUser = JSON.parse(localStorage.getItem('user'));

class AuthService {
  login(user) {
    return axios.post(API_URL + "signin", user).then(response => {
        if (response.data.token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return Promise.resolve(response.data);
      });
  }

  logout() {localStorage.removeItem("user");
  }

  register(user) {return axios.post(API_URL + "signup", user).then(response=>{return Promise.resolve(response.data)});
  }
    async getUser(){
      if (localUser){
            let user=new FormData();
            user.append('personId',localUser.id)
        return  await  axios.post(API_URL+`persons`,user,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
      }else
            return null
    }
    async getPersonsByRoleName(roleName){
      let formData=new FormData();
      formData.append("roleName",roleName)
      return  await  axios.put(API_URL+`persons`,formData,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    async updatePerson(person){
        return  await  axios.patch(API_URL+`persons`,person,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
    async deletePerson(person){
        return  await  axios.post(API_URL+`persons/delete`,person,{headers:{'Authorization':authHeader() ,'Content-Type': 'multipart/form-data'}}).then(response=>{return Promise.resolve(response.data)})
    }
}
export default new AuthService();
