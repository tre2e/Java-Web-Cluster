<template>
  <div class="play-container">
    <div class="back-link">
      <router-link to="/list">← 回到视频列表</router-link>
    </div>
    <div class="video-title">{{ videoInfo?.title || '视频播放' }}</div>

    <div class="ad-container" v-if="showAd && currentAd">
      <video
          class="video-player ad-player"
          v-if="currentAd.adType === 'video'"
          autoplay
          @ended="handleAdEnd"
          ref="adVideoRef"
          playsinline
      >
        <source :src="currentAd.resourceUrl" type="video/mp4">
      </video>
      <img
          class="video-player ad-img"
          v-else
          :src="currentAd.resourceUrl"
          :alt="currentAd.adTitle || '广告'"
      >

      <div class="skip-ad-btn" @click="skipAd" v-if="canSkipAd">
        跳过广告 ({{ adCountdown }}s)
      </div>
      <div class="skip-ad-btn disabled" v-else>
        跳过广告 ({{ adCountdown }}s)
      </div>
    </div>

    <video
        class="video-player"
        controls
        preload="auto"
        :src="getVideoUrl(videoInfo?.path)"
        v-if="!showAd && videoInfo"
        @timeupdate="handleVideoTimeUpdate"
        ref="mainVideoRef"
        playsinline
    >
      你的浏览器不支持HTML5视频播放
    </video>
  </div>
</template>

<script>
import request from '../utils/request';
import { getLeftAd } from '../utils/AdApi';
import { getVideoUrl } from '../utils/pathUtil';

export default {
  name: 'VideoPlay',
  props: { id: { type: [Number, String], required: true } },
  data() {
    return {
      videoInfo: null,
      showAd: true,
      currentAd: null,
      adCountdown: 0,
      canSkipAd: false,
      adTimer: null,
      hasPlayedMidAd: false,
      mainVideoRef: null,
      adVideoRef: null,
      videoCurrentTime: 0
    };
  },
  async mounted() {
    await Promise.all([
      this.getVideoInfo(Number(this.id)),
      this.loadAd()
    ]);
    this.initAd();
    this.mainVideoRef = this.$refs.mainVideoRef;
    this.adVideoRef = this.$refs.adVideoRef;
  },
  beforeUnmount() {
    clearInterval(this.adTimer);
    this.adTimer = null;
  },
  updated() {
    this.mainVideoRef = this.$refs.mainVideoRef;
    this.adVideoRef = this.$refs.adVideoRef;
  },
  methods: {
    getVideoUrl,
    async loadAd() {
      try {
        const adRes = await getLeftAd();
        this.currentAd = adRes.data || null;
        // 完全移除URL拼接（保留你的原始逻辑，广告接口已返回完整URL）
      } catch (error) {
        console.error("广告加载失败：", error);
        this.currentAd = null;
      }
    },
    // 恢复你原始的视频信息获取逻辑（关键！修复视频不可用问题）
    async getVideoInfo(id) {
      try {
        const res = await request.get(`/api/video/${id}`);
        this.videoInfo = res.data || res; // 完全保留你的原始解析逻辑
      } catch (error) {
        console.error("获取视频信息失败：", error);
        alert("视频不存在或已被删除");
        this.$router.push('/list');
      }
    },
    initAd() {
      if (!this.currentAd) {
        this.showAd = false;
        return;
      }
      this.adCountdown = this.currentAd.adType === 'video' ? 5 : 10;
      this.canSkipAd = false;
      setTimeout(() => {
        this.canSkipAd = true;
      }, 5000);
      this.startAdCountdown();
    },
    startAdCountdown() {
      if (this.adTimer) clearInterval(this.adTimer);
      this.adTimer = setInterval(() => {
        if (this.adCountdown <= 0) {
          this.handleAdEnd();
          return;
        }
        this.adCountdown--;
      }, 1000);
    },
    skipAd() {
      if (!this.canSkipAd || !this.currentAd || !this.showAd) return;
      this.closeAd();
    },
    handleAdEnd() {
      if (!this.showAd) return;
      this.closeAd();
    },
    closeAd() {
      if (this.adTimer) {
        clearInterval(this.adTimer);
        this.adTimer = null;
      }
      this.showAd = false;
      this.canSkipAd = false;

      this.$nextTick(() => {
        if (this.mainVideoRef) {
          if (this.videoCurrentTime > 0) {
            this.mainVideoRef.currentTime = this.videoCurrentTime;
          }
          this.mainVideoRef.play().catch(err => {
            console.log("自动播放失败（浏览器策略限制）：", err);
          });
        }
      });
    },
    handleVideoTimeUpdate() {
      if (!this.mainVideoRef || this.hasPlayedMidAd || this.showAd) return;

      const { duration, currentTime } = this.mainVideoRef;
      if (duration <= 0 || currentTime <= 0) return;

      this.videoCurrentTime = currentTime;

      if (currentTime / duration >= 0.5) {
        this.playMidAd();
        this.hasPlayedMidAd = true;
      }
    },
    async playMidAd() {
      if (this.mainVideoRef) {
        this.videoCurrentTime = this.mainVideoRef.currentTime;
        this.mainVideoRef.pause();
      }

      await this.loadAd();
      if (!this.currentAd) {
        if (this.mainVideoRef) {
          this.mainVideoRef.currentTime = this.videoCurrentTime;
          this.mainVideoRef.play();
        }
        return;
      }

      this.showAd = true;
      this.adCountdown = this.currentAd.adType === 'video' ? 5 : 10;
      this.canSkipAd = false;
      setTimeout(() => {
        this.canSkipAd = true;
      }, 5000);
      this.startAdCountdown();
    }
  }
};
</script>

<style scoped>
@import url('../assets/css/video_play.css');
</style>