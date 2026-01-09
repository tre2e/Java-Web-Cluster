package com.example.video.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义文件存储配置类：读取application.properties中的配置
 */
@Configuration
public class FileStorageConfig {

    // 读取配置文件中的文件本地存储路径
    @Value("${file.local-path}")
    private String localPath;

    // 视频存储子目录
    public String getVideoStoragePath() {
        return localPath + "/videos";
    }

    // 封面存储子目录
    public String getThumbnailStoragePath() {
        return localPath + "/thumbnails";
    }

    // 获取根路径（对外暴露）
    public String getLocalRootPath() {
        return localPath;
    }
}