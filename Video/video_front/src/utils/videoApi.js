// src/utils/videoApi.js
import request from './request'; // 同目录引入request

// 视频上传
export const uploadVideo = (title, sort, videoFile, thumbnailFile) => {
    if (!title || title.trim() === '') {
        return Promise.reject(new Error('视频标题不能为空'));
    }
    if (!sort || sort.trim() === '') {
        return Promise.reject(new Error('视频分类不能为空'));
    }
    if (!videoFile) {
        return Promise.reject(new Error('请选择视频文件'));
    }
    if (!thumbnailFile) {
        return Promise.reject(new Error('请选择封面文件'));
    }

    const formData = new FormData();
    formData.append('title', title.trim());
    formData.append('sort', sort.trim());
    formData.append('videoFile', videoFile);
    formData.append('thumbnailFile', thumbnailFile);

    return request.post('/api/video/upload', formData);
};

// 获取视频列表
export const getVideoList = () => {
    return request.get('/api/video/getAll');
};

// 根据ID获取视频
export const getVideoById = (id) => {
    return request.get(`/api/video/${id}`);
};