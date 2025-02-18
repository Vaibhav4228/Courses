
import axios from "axios";

class UserService {
  static BASE_URL = "http://localhost:9500";


  static async login(username, password) {
    try {
   
      const response = await axios.post(`${UserService.BASE_URL}/auth/login`, {
        username,
        password,
      });
      return response.data; 
    } catch (err) {
      throw new Error(err.response?.data || "Login failed");
    }
  }

  static async register(userData) {
    try {
    
      const response = await axios.post(
        `${UserService.BASE_URL}/auth/signup`,
        userData
      );
      return response.data;
    } catch (err) {
      throw new Error(err.response?.data || "Signup failed");
    }
  }
}

export default UserService;
