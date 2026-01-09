package com.example.video.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebResourceConfig implements WebMvcConfigurer {

    // 从配置文件读取视频存储根路径
    @Value("${file.local-path}")
    private String LOCAL_STORAGE_PATH;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // ========== 1. 视频/封面资源映射（优先级最高，避免被覆盖） ==========
        registry.addResourceHandler("/videos/**")
                .addResourceLocations("file:" + LOCAL_STORAGE_PATH + "/videos/");

        registry.addResourceHandler("/thumbnails/**")
                .addResourceLocations("file:" + LOCAL_STORAGE_PATH + "/thumbnails/");

        // ========== 2. 前端静态资源映射（解决favicon.ico/静态文件404） ==========
        // 映射所有前端静态资源（css/js/img/favicon.ico等）
        registry.addResourceHandler("/**/*.ico", "/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg", "/**/*.jpeg")
                .addResourceLocations("classpath:/static/");

        // ========== 3. 前端路由兜底（解决/list等路由404） ==========
        // 所有未匹配到的路径（如/list、/detail）都指向index.html，由前端路由处理
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new org.springframework.web.servlet.resource.PathResourceResolver() {
                    @Override
                    protected org.springframework.core.io.Resource getResource(String resourcePath, org.springframework.core.io.Resource location) throws java.io.IOException {
                        org.springframework.core.io.Resource requestedResource = location.createRelative(resourcePath);
                        // 如果请求的资源不存在，返回index.html
                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource : location.createRelative("index.html");
                    }
                });
    }
}