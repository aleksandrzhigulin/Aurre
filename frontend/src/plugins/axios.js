import axios from 'axios';
var jwt = "";
if (getCookie("jwt") != null) {
  jwt = "Bearer " + getCookie('jwt');
}
export const axiosInstance = axios.create({
    headers: {'Authorization': jwt}
  });

