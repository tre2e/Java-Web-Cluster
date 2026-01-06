<!-- src/App.vue -->
<template>
  <div id="app">
    <!-- 导航栏 -->
    <div class="nav">
      <a
          href="#/publish"
          @click.prevent="goTo('/publish')"
          :class="$route.path === '/publish' ? 'active' : ''"
      >
        广告发布
      </a>
      <a
          href="#/list"
          @click.prevent="goTo('/list')"
          :class="$route.path === '/list' ? 'active' : ''"
      >
        广告列表
      </a>
    </div>
    <!-- 路由出口 -->
    <router-view />
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()

// 路由跳转逻辑
const goTo = (path) => {
  router.push(path)
}
</script>

<style>
/* 引入全局样式 */
@import './global.css';

/* 导航栏样式 */
#app {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.nav {
  margin-bottom: 20px;
  border-bottom: 1px solid #e6e6e6;
  padding-bottom: 10px;
}

.nav a {
  margin-right: 20px;
  text-decoration: none;
  font-size: 16px;
  color: #666;
}

.nav a.active {
  color: #409eff;
}
</style>