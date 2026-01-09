// src/router/index.js（你的原代码，一字未改）
import { createRouter, createWebHistory } from 'vue-router';
import List from '../views/List.vue';
import Share from '../views/Share.vue';
import VideoPlay from '../views/VideoPlay.vue';

const routes = [
    { path: '/', redirect: '/list' },
    { path: '/list', name: 'List', component: List },
    { path: '/share', name: 'Share', component: Share },
    { path: '/video/play/:id', name: 'VideoPlay', component: VideoPlay, props: true }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;