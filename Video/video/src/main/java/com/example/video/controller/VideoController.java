package com.example.video.controller;

import com.example.video.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    // ========== 关键修改：将/upload路由放在最前面，避免被/{id}覆盖 ==========
    @PostMapping("/upload")
    public String uploadVideo(
            @RequestParam("title") String title,
            @RequestParam("sort") String sort,
            @RequestParam("videoFile") MultipartFile videoFile,
            @RequestParam("thumbnailFile") MultipartFile thumbnailFile
    ) {
        try {
            videoService.uploadVideo(title, sort, videoFile, thumbnailFile);
            return "上传成功";
        } catch (Exception e) {
            log.error("视频上传失败", e);
            return "上传失败：" + e.getMessage();
        }
    }

    // ========== 列表接口放在动态路由前面 ==========
    @GetMapping("/getAll")
    public Object getAllVideos() {
        return videoService.listAllVideos();
    }

    // ========== 动态路由/{id}放在最后 ==========
    @GetMapping("/{id}")
    public Object getVideoById(@PathVariable("id") Integer id) {
        return videoService.getVideoById(id);
    }
}