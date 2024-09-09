// apiService.js
import axios from "axios";

// 创建axios实例并配置基础URL
const apiClient = axios.create({
    baseURL: "http://localhost:80/api",
    headers: {
        "Content-Type": "application/json"
    },
});

export default {
    // 封装Get接口
    get(fetchUrl) {
        return apiClient.get(fetchUrl);
    }
};
