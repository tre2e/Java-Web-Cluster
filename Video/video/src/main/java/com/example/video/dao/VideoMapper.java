package com.example.video.dao;

import com.example.video.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface VideoMapper {
    void insertVideo(Video video);
    Video getVideoById(Integer id);
    List<Video> listAllVideos();
    void updateVideoById(Video video);
    void deleteVideoById(Integer id);
}