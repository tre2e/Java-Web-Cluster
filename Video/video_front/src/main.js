// src/main.js（你的原有代码 + 仅修正request引入路径）
import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index.js' // 明确指向router目录下的index.js
import request from './utils/request' // 引入src/utils/request.js

const app = createApp(App);
app.use(router);
app.config.globalProperties.$http = request;
app.mount('#app');