<template>
  <div class="share-container">
    <div class="header">
      <div class="header-title">视频上传</div>
      <div class="share-link">
        <router-link to="/list">返回视频列表</router-link>
      </div>
    </div>

    <div class="upload-container">
      <form @submit.prevent="handleUpload">
        <div class="form-item">
          <label for="title">视频标题</label>
          <input type="text" id="title" v-model="formData.title" placeholder="请输入标题" required>
        </div>
        <div class="form-item">
          <label for="sort">视频分类</label>
          <select id="sort" v-model="formData.sort" required>
            <option value="" disabled>请选择分类</option>
            <option value="科技">科技</option>
            <option value="生活">生活</option>
            <option value="美食">美食</option>
            <option value="游戏">游戏</option>
            <option value="影视">影视</option>
          </select>
        </div>
        <div class="form-item">
          <label for="videoFile">视频文件</label>
          <input type="file" id="videoFile" accept="video/*" @change="handleVideoFileChange" required>
        </div>
        <div class="form-item">
          <label for="thumbnailFile">封面文件</label>
          <input type="file" id="thumbnailFile" accept="image/*" @change="handleThumbnailFileChange" required>
        </div>
        <button type="submit" class="upload-btn">上传视频</button>
      </form>
    </div>
  </div>
</template>

<script>
import request from '../utils/request';

export default {
  name: 'Share',
  data() {
    return {
      formData: { title: '', sort: '' },
      videoFile: null,
      thumbnailFile: null
    };
  },
  methods: {
    handleVideoFileChange(e) {this.videoFile = e.target.files[0];},
    handleThumbnailFileChange(e) {this.thumbnailFile = e.target.files[0];},
    async handleUpload() {
      if (!this.formData.title || !this.formData.sort || !this.videoFile || !this.thumbnailFile) {
        alert("请填写完整信息并选择文件");
        return;
      }

      const formData = new FormData();
      formData.append('title', this.formData.title);
      formData.append('sort', this.formData.sort);
      formData.append('videoFile', this.videoFile);
      formData.append('thumbnailFile', this.thumbnailFile);

      try {
        // 👇 仅新增：添加headers配置，让axios自动生成FormData请求头（核心修复）
        const res = await request.post('/api/video/upload', formData, {
          headers: { 'Content-Type': undefined }, // 覆盖全局Content-Type，解决400错误
          timeout: 600000
        });
        alert(res.data.msg);
        if (res.data.code === 200) {
          this.formData = { title: '', sort: '' };
          document.getElementById('videoFile').value = '';
          document.getElementById('thumbnailFile').value = '';
          this.$router.push('/list');
        }
      } catch (error) {
        console.error("上传失败详情：", error.response || error);
        const errMsg = error.response?.data?.msg || (error.message || "文件过大或网络异常");
        alert("上传失败：" + errMsg);
      }
    }
  }
};
</script>
<style scoped>
@import url('../assets/css/share.css');
</style>