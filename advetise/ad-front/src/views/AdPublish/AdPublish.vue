<!-- src/views/AdPublish/AdPublish.vue -->
<template>
  <div class="card">
    <h3>广告发布（图片/视频）</h3>

    <form ref="adFormRef" @submit.prevent="handleSubmit">
      <!-- 广告标题 -->
      <div class="form-item">
        <label>广告标题：</label>
        <input
            type="text"
            v-model="adForm.adTitle"
            placeholder="请输入标题"
            @blur="validateTitle"
        >
        <span v-if="titleError" class="error-tip">{{ titleError }}</span>
      </div>

      <!-- 广告类型 -->
      <div class="form-item">
        <label>广告类型：</label>
        <label style="margin-right: 20px; cursor: pointer;">
          <input
              type="radio"
              name="adType"
              value="image"
              v-model="adForm.adType"
              style="margin-right: 5px;"
          >
          图片
        </label>
        <label style="cursor: pointer;">
          <input
              type="radio"
              name="adType"
              value="video"
              v-model="adForm.adType"
              style="margin-right: 5px;"
          >
          视频
        </label>
      </div>

      <!-- 资源URL -->
      <div class="form-item">
        <label>资源URL：</label>
        <input
            type="text"
            v-model="adForm.resourceUrl"
            :placeholder="adForm.adType === 'image' ? '请输入图片URL（http/https开头）' : '请输入视频URL（http/https开头）'"
            @blur="validateUrl"
        >
        <span v-if="urlError" class="error-tip">{{ urlError }}</span>
      </div>

      <!-- 视频时长 -->
      <div v-if="adForm.adType === 'video'" class="form-item">
        <label>视频时长：</label>
        <input
            type="number"
            v-model="adForm.videoDuration"
            placeholder="单位：秒"
            min="1"
        >
      </div>

      <!-- 按钮 -->
      <div class="form-item" style="margin-top: 20px; text-align: center;">
        <button type="submit" class="btn btn-primary">发布广告</button>
        <button type="button" @click="handleReset" class="btn btn-default">重置表单</button>
      </div>
    </form>
  </div>
</template>

<script setup>
// 引入独立JS逻辑
import { adForm, titleError, urlError, validateTitle, validateUrl, handleSubmit, handleReset } from './ad-publish.js'
</script>

<style scoped>
/* 引入独立CSS样式 */
@import './ad-publish.css';
</style>