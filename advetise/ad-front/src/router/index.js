// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router'
import AdPublish from '../views/AdPublish/AdPublish.vue'
import AdList from '../views/AdList/AdList.vue'

const routes = [
    { path: '/', redirect: '/publish' },
    { path: '/publish', name: 'AdPublish', component: AdPublish },
    { path: '/list', name: 'AdList', component: AdList }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router