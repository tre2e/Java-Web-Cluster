// src/utils/request.js 完整正确版本
import axios from 'axios';

// 核心：纯英文单引号、无乱码、无重复的baseURL
const request = axios.create({
    baseURL: 'http://10.100.164.21:1000', // 仅这一行，无任何多余字符
    timeout: 600000, // 适配大文件上传超时
    withCredentials: false
});

// 请求拦截器：移除所有自定义Content-Type，避免覆盖FormData
request.interceptors.request.use(
    (config) => {
        // 关键：删除任何手动设置的Content-Type
        if (config.headers) {
            delete config.headers['Content-Type'];
        }
        return config;
    },
    (error) => {
        console.error('请求拦截器错误：', error);
        return Promise.reject(error);
    }
);

// 响应拦截器：统一处理返回值
request.interceptors.response.use(
    (response) => {
        // 兼容后端Result格式（code/msg/data）
        return {
            code: response.data.code || 200,
            msg: response.data.msg || '操作成功',
            data: response.data.data || response.data
        };
    },
    (error) => {
        console.error('响应错误：', error);
        return Promise.reject({
            code: error.response?.status || 500,
            msg: error.response?.data?.msg || error.message || '服务器异常'
        });
    }
);

export default request;