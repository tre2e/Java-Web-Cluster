// src/utils/AdApi.js
import request from './request';

// 左侧广告（List/Play页共用）
export const getLeftAd = async () => {
    try {
        // 直接请求广告服务8082端口（避免视频服务转发的兼容问题）
        const res = await request.get('http://10.100.164.21:8082/api/ad/get', {
            params: { type: 'left' }
        });
        // 严格适配你List.vue中`leftAdRes.data`的取值逻辑
        return {
            data: res.data || null, // 对应你代码中的leftAdRes.data
            code: res.code || 200,
            msg: res.msg || 'success'
        };
    } catch (error) {
        console.error('左侧广告加载失败：', error);
        return { data: null }; // 兜底避免前端报错
    }
};

// 右侧广告（仅List页）
export const getRightAd = async () => {
    try {
        const res = await request.get('http://10.100.164.21:8082/api/ad/get', {
            params: { type: 'right' }
        });
        return {
            data: res.data || null, // 对应你代码中的rightAdRes.data
            code: res.code || 200,
            msg: res.msg || 'success'
        };
    } catch (error) {
        console.error('右侧广告加载失败：', error);
        return { data: null };
    }
};