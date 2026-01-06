// src/views/AdList/ad-list.js
import { ref } from 'vue'
import { listAllAds } from '@/api/adApi.js'

// 加载状态（暴露给模板）
export const loading = ref(false)
// 广告列表数据（暴露给模板）
export const adList = ref([])

// 时间格式化逻辑
export const formatTime = (timeStr) => {
    if (!timeStr) return '暂无时间'
    try {
        return new Date(timeStr).toLocaleString()
    } catch (e) {
        return '格式错误'
    }
}

// 获取列表数据逻辑
export const getAdList = async () => {
    loading.value = true
    try {
        const res = await listAllAds()
        adList.value = res.data.code === 200 ? res.data.data : []
    } catch (e) {
        alert('查询失败，请检查后端服务！')
        adList.value = []
    } finally {
        loading.value = false
    }
}