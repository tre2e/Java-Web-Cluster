package com.example.video.service;

import com.example.video.entity.Video;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface VideoService {
    List<Video> listAllVideos();
    Video getVideoById(Integer id);
    void uploadVideo(String title, String sort, MultipartFile videoFile, MultipartFile thumbnailFile) throws Exception;
    void deleteVideoById(Integer id) throws Exception;
    void updateVideoById(Video video);
}