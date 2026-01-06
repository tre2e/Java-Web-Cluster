<!-- src/views/AdList/AdList.vue -->
<template>
  <div class="card">
    <h3>广告列表</h3>

    <!-- 加载状态 -->
    <div v-if="loading" class="loading">加载中...</div>

    <!-- 广告列表表格 -->
    <table v-else class="ad-table">
      <thead>
      <tr>
        <th>广告ID</th>
        <th>标题</th>
        <th>类型</th>
        <th>资源URL</th>
        <th>时长</th>
        <th>发布时间</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in adList" :key="item.adId">
        <td>{{ item.adId }}</td>
        <td>{{ item.adTitle }}</td>
        <td>
            <span :class="item.adType === 'image' ? 'tag success' : 'tag primary'">
              {{ item.adType === 'image' ? '图片' : '视频' }}
            </span>
        </td>
        <td>{{ item.resourceUrl }}</td>
        <td>{{ item.videoDuration ? item.videoDuration + '秒' : '—' }}</td>
        <td>{{ formatTime(item.createTime) }}</td>
      </tr>
      <tr v-if="adList.length === 0">
        <td colspan="6" class="empty-tip">暂无广告数据</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
// 引入独立JS逻辑
import { loading, adList, formatTime, getAdList } from './ad-list.js'
// 页面挂载时加载数据
import { onMounted } from 'vue'
onMounted(() => getAdList())
</script>

<style scoped>
/* 引入独立CSS样式 */
@import './ad-list.css';
</style>