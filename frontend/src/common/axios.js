import axios from "axios";

const axios_api = axios.create({
  // baseURL: "http://localhost:8080/v1/api",
  baseURL: "http://3.227.24.100:5000/v1/api",
});

export default axios_api;
