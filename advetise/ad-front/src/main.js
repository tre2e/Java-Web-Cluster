// src/main.js
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
// 引入全局样式
import './global.css'

const app = createApp(App)
app.use(router)
app.config.globalProperties.$axios = axios
app.mount('#app')