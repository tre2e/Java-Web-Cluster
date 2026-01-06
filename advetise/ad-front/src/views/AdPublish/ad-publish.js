// src/views/AdPublish/ad-publish.js
import { ref, reactive } from 'vue'
import { addAd } from '@/api/adApi.js'

// 表单数据（暴露给模板）
export const adForm = reactive({
    adTitle: '',
    adType: 'image',
    resourceUrl: '',
    videoDuration: null
})

// 校验错误提示（暴露给模板）
export const titleError = ref('')
export const urlError = ref('')

// 标题校验逻辑
export const validateTitle = () => {
    if (!adForm.adTitle) {
        titleError.value = '标题不能为空'
    } else if (adForm.adTitle.length < 2 || adForm.adTitle.length > 50) {
        titleError.value = '标题长度2-50字符'
    } else {
        titleError.value = ''
    }
}

// URL校验逻辑
export const validateUrl = () => {
    if (!adForm.resourceUrl) {
        urlError.value = 'URL不能为空'
    } else if (!adForm.resourceUrl.startsWith('http')) {
        urlError.value = 'URL需以http/https开头'
    } else {
        urlError.value = ''
    }
}

// 发布广告逻辑
export const handleSubmit = async () => {
    // 先执行校验
    validateTitle()
    validateUrl()
    if (titleError.value || urlError.value) return

    try {
        const res = await addAd(adForm)
        if (res.data.code === 200) {
            alert('广告发布成功！')
            handleReset()
        } else {
            alert('发布失败：' + res.data.msg)
        }
    } catch (e) {
        alert('发布失败，请检查后端服务！')
    }
}

// 重置表单逻辑
export const handleReset = () => {
    adForm.adTitle = ''
    adForm.adType = 'image'
    adForm.resourceUrl = ''
    adForm.videoDuration = null
    titleError.value = ''
    urlError.value = ''
}