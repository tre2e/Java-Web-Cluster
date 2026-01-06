// src/api/adApi.js
import axios from 'axios'

const request = axios.create({
    baseURL: '/api',
    timeout: 5000
})

// 新增广告
export const addAd = (adForm) => {
    return request.post('/admin/ad/add', adForm)
}

// 查询广告列表
export const listAllAds = () => {
    return request.get('/admin/ad/list')
}