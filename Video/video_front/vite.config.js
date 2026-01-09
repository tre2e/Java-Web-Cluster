import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { fileURLToPath, URL } from 'node:url'

export default defineConfig({
    plugins: [vue()],
    // 核心：基础路径设为根路径，支持前端路由
    base: '/',
    resolve: {
        alias: {
            '@': fileURLToPath(new URL('./src', import.meta.url))
        }
    },
    server: {
        port: 5173,
        // 本地开发代理（合并后无需，但保留不影响）
        proxy: {
            '/api': {
                target: 'http://localhost:1000',
                changeOrigin: true
            }
        }
    },
    // 打包配置（确保静态资源路径正确）
    build: {
        assetsDir: 'assets',
        outDir: 'dist',
        rollupOptions: {
            output: {
                entryFileNames: 'assets/[name].[hash].js',
                chunkFileNames: 'assets/[name].[hash].js',
                assetFileNames: 'assets/[name].[hash].[ext]'
            }
        }
    }
})