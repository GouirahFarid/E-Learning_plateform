import axios from "axios";
import authHeader from "./auth-header";
const user = JSON.parse(localStorage.getItem("user"));
const API_URL = "http://localhost:9001/api/";
class UserService {
  getPublicContent() {
    return axios.get(API_URL + `profile/${user.id}`, { headers: authHeader() });
  }
  getUserBoard() {
    return axios.get(API_URL + "user", { headers: authHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_URL + "mod", { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + "admin", { headers: authHeader() });
  }
}

export default new UserService();
