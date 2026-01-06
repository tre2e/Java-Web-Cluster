package com.example.ad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 允许所有域名访问（生产环境建议指定具体域名，如 "http://xxx.com"）
        config.addAllowedOriginPattern("*");
        // 允许跨域请求携带Cookie
        config.setAllowCredentials(true);
        // 允许所有请求方法（GET/POST等）
        config.addAllowedMethod("*");
        // 允许所有请求头
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 对所有接口生效
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}