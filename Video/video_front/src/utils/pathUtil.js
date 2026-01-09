// src/utils/pathUtil.js（纯干净版本）
// 基地址：无引号、无乱码、纯字符串
const BASE_URL = 'http://10.100.164.21:1000';

// 视频路径拼接（仅拼接基地址+数据库路径）
export const getVideoUrl = (path) => {
    if (!path) return '';
    // 防止路径重复：如果path已包含BASE_URL，直接返回
    if (path.startsWith('http')) return path;
    // 拼接纯路径：BASE_URL + /videos/xxx.mp4
    return `${BASE_URL}${path}`;
};

// 封面路径拼接
export const getThumbnailUrl = (thumbnailPath) => {
    if (!thumbnailPath) return `${BASE_URL}/assets/images/no-preview.jpg`;
    if (thumbnailPath.startsWith('http')) return thumbnailPath;
    return `${BASE_URL}${thumbnailPath}`;
};

// 广告图片路径（修复默认广告看不到的问题）
export const getAdImageUrl = (adPath) => {
    if (!adPath) return `${BASE_URL}/assets/images/default_ad.jpg`;
    if (adPath.startsWith('http')) return adPath;
    return `${BASE_URL}${adPath}`;
};

export { BASE_URL };