import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "https://apis.openapi.sk.com/tmap",
  headers: {
    // "Content-type": "application/json",
    Accept: "application/json",
    appKey: "l7xx3b53954d39e14bde9013c62283bdba29",
  },
});
