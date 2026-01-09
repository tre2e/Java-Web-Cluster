<template>
  <div class="list-container">
    <div class="header">
      <div class="header-title">欢迎你的到来</div>
      <div class="share-link">
        <router-link to="/share">点击此处分享你的视频</router-link>
      </div>
    </div>

    <div class="category-filter">
      <div class="category-list">
        <div
            class="category-item"
            :class="{ active: currentCategory === item }"
            v-for="item in videoCategories"
            :key="item"
            @click="handleCategoryChange(item)"
        >
          {{ item }}
        </div>
      </div>
    </div>

    <div class="content">
      <div class="ad-left" v-if="leftAd && leftAd.resourceUrl">
        <video
            class="ad-video"
            v-if="leftAd.adType === 'video'"
            autoplay loop muted
        >
          <source :src="leftAd.resourceUrl" type="video/mp4">
        </video>
        <img
            class="ad-img"
            v-else
            :src="leftAd.resourceUrl"
            :alt="leftAd.adTitle || '左侧广告'"
        >
        <div class="ad-tip">左侧广告</div>
      </div>

      <div class="video-wrapper">
        <div class="video-grid" v-if="filteredVideoList.length > 0">
          <div class="video-item" v-for="video in filteredVideoList" :key="video.id">
            <router-link class="video-link" :to="`/video/play/${video.id}`">
              <img
                  class="video-thumb"
                  :src="getThumbnailUrl(video.thumbnail)"
                  alt="视频封面"
                  v-if="video.thumbnail"
              >
              <img class="video-thumb" src="../../public/assets/images/no-preview.jpg" alt="默认封面" v-else>
            </router-link>
            <div class="video-info">
              <div class="video-title">{{ video.title }}</div>
              <div class="video-sort">{{ video.sort }}</div>
            </div>
          </div>
        </div>
        <div class="video-empty" v-else>
          <p>暂无{{ currentCategory === '全部视频' ? '' : currentCategory }}类视频，请先上传</p>
        </div>
      </div>

      <div class="ad-right" v-if="rightAd && rightAd.resourceUrl">
        <video
            class="ad-video"
            v-if="rightAd.adType === 'video'"
            autoplay loop muted
        >
          <source :src="rightAd.resourceUrl" type="video/mp4">
        </video>
        <img
            class="ad-img"
            v-else
            :src="rightAd.resourceUrl"
            :alt="rightAd.adTitle || '右侧广告'"
        >
        <div class="ad-tip">右侧广告</div>
      </div>
    </div>
  </div>
</template>

<script>
import request from '../utils/request';
import { getLeftAd, getRightAd } from '../utils/AdApi';
import {getThumbnailUrl} from "../utils/pathUtil.js";

export default {
  name: 'List',
  data() {
    return {
      videoList: [],
      currentCategory: "全部视频",
      leftAd: null,
      rightAd: null,
      videoCategories: ["全部视频", "科技", "生活", "美食", "游戏", "影视"]
    };
  },
  computed: {
    filteredVideoList() {
      // 👇 仅修复：过滤逻辑优化（原逻辑可能过滤掉有效视频）
      return this.currentCategory === "全部视频"
          ? this.videoList.filter(video => !!video) // 仅过滤null/undefined
          : this.videoList.filter(video => !!video && video.sort === this.currentCategory);
    }
  },
  mounted() {
    this.getVideoList();
    this.loadAds();
  },
  watch: {
    $route(to) {
      if (to.path === '/list') {
        this.getVideoList();
      }
    }
  },
  methods: {
    getThumbnailUrl,
    async getVideoList() {
      try {
        const res = await request.get('/api/video/getAll');
        // 👇 仅修复：适配后端返回结构（若返回Result对象，取res.data.data）
        this.videoList = Array.isArray(res.data.data || res.data) ? (res.data.data || res.data) : [];
      } catch (error) {
        console.error("视频列表获取失败：", error);
        alert("获取视频列表失败，请刷新重试");
        this.videoList = [];
      }
    },
    async loadAds() {
      try {
        const leftAdRes = await getLeftAd();
        const rightAdRes = await getRightAd();
        this.leftAd = leftAdRes.data || null;
        this.rightAd = rightAdRes.data || null;
      } catch (error) {
        console.error("广告加载失败：", error);
        this.leftAd = null;
        this.rightAd = null;
      }
    },
    handleCategoryChange(category) {
      this.currentCategory = category;
    },
  },
  activated() {
    if (!this.leftAd || !this.rightAd) {
      this.loadAds();
    }
  }
};

</script>

<style scoped>
@import url('../assets/css/list.css');
</style>