package com.example.video.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 全局跨域配置（解决403 Forbidden）
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 1. 创建跨域配置对象
        CorsConfiguration config = new CorsConfiguration();
        // 允许所有源（测试阶段用，生产可指定具体地址，如http://localhost:5173）
        config.addAllowedOriginPattern("*");
        // 允许携带Cookie（关键，否则会403）
        config.setAllowCredentials(true);
        // 允许所有请求方法（GET/POST/DELETE等）
        config.addAllowedMethod("*");
        // 允许所有请求头
        config.addAllowedHeader("*");
        // 预检请求有效期（秒），避免频繁OPTIONS请求
        config.setMaxAge(3600L);

        // 2. 配置跨域路径（覆盖所有接口）
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // /** 覆盖所有接口，包括/api/video/*、/api/ad/*

        // 3. 返回跨域过滤器
        return new CorsFilter(source);
    }
}