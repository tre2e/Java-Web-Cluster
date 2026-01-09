package com.example.video.service.impl;

import com.example.video.config.FileStorageConfig;
import com.example.video.dao.VideoMapper;
import com.example.video.entity.Video;
import com.example.video.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    // 注入自定义配置类（读取application.properties中的路径）
    @Autowired
    private FileStorageConfig fileStorageConfig;

    @Override
    public List<Video> listAllVideos() {
        return videoMapper.listAllVideos();
    }

    @Override
    public Video getVideoById(Integer id) {
        if (id == null || id <= 0) {
            return null;
        }
        return videoMapper.getVideoById(id);
    }

    /**
     * 上传文件（适配Linux路径+配置文件读取）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void uploadVideo(String title, String sort, MultipartFile videoFile, MultipartFile thumbnailFile) throws Exception {
        // 1. 基础校验
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("视频标题不能为空");
        }
        if (videoFile == null || videoFile.isEmpty()) {
            throw new IllegalArgumentException("视频文件不能为空");
        }
        if (thumbnailFile == null || thumbnailFile.isEmpty()) {
            throw new IllegalArgumentException("封面文件不能为空");
        }

        // 2. 生成UUID文件名（避免冲突）
        String videoSuffix = getFileSuffix(videoFile.getOriginalFilename());
        String videoFileName = UUID.randomUUID().toString() + videoSuffix;
        String thumbnailSuffix = getFileSuffix(thumbnailFile.getOriginalFilename());
        String thumbnailFileName = UUID.randomUUID().toString() + thumbnailSuffix;
        log.info("生成存储文件名：视频={}，封面={}", videoFileName, thumbnailFileName);

        // 3. 从配置类获取存储目录（适配Linux路径 /usr/local/video/local-files）
        String rootPath = fileStorageConfig.getLocalRootPath();
        File videoDir = new File(rootPath, "videos");
        File thumbnailDir = new File(rootPath, "thumbnails");
        if (!videoDir.exists()) {
            boolean created = videoDir.mkdirs();
            log.info("创建视频目录 {}：{}", videoDir.getAbsolutePath(), created ? "成功" : "失败");
        }
        if (!thumbnailDir.exists()) {
            boolean created = thumbnailDir.mkdirs();
            log.info("创建封面目录 {}：{}", thumbnailDir.getAbsolutePath(), created ? "成功" : "失败");
        }

        // 4. 构建文件完整路径
        File videoDestFile = new File(videoDir, videoFileName);
        File thumbnailDestFile = new File(thumbnailDir, thumbnailFileName);
        log.info("Linux视频文件存储路径：{}", videoDestFile.getAbsolutePath());
        log.info("Linux封面文件存储路径：{}", thumbnailDestFile.getAbsolutePath());

        // 5. 写入文件
        writeFile(videoFile, videoDestFile, "视频");
        writeFile(thumbnailFile, thumbnailDestFile, "封面");

        // 6. 验证文件是否写入成功
        if (videoDestFile.length() == 0) {
            throw new Exception("视频上传失败：文件大小为0");
        }
        if (thumbnailDestFile.length() == 0) {
            throw new Exception("封面上传失败：文件大小为0");
        }

        // 7. 数据库存储「前端可访问的相对路径」（关键）
        Video video = new Video();
        video.setTitle(title);
        video.setSort(sort);
        video.setFilename(videoFileName);
        video.setPath("/videos/" + videoFileName); // 对应WebResourceConfig的映射
        video.setThumbnail("/thumbnails/" + thumbnailFileName);
        video.setUpload_time(LocalDateTime.now());
        videoMapper.insertVideo(video);
        log.info("视频上传Linux服务器完成：ID={}", video.getId());
    }

    /**
     * 删除文件（适配Linux路径+配置文件）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteVideoById(Integer id) throws Exception {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("视频ID无效");
        }
        Video video = videoMapper.getVideoById(id);
        if (video == null) {
            throw new Exception("视频不存在，ID=" + id);
        }

        // 从相对路径还原Linux绝对路径：/videos/xxx.mp4 → /usr/local/video/local-files/videos/xxx.mp4
        String videoLocalPath = fileStorageConfig.getLocalRootPath() + video.getPath();
        String thumbnailLocalPath = fileStorageConfig.getLocalRootPath() + video.getThumbnail();

        File videoFile = new File(videoLocalPath);
        if (videoFile.exists() && videoFile.delete()) {
            log.info("删除Linux视频文件成功：{}", videoLocalPath);
        } else {
            log.warn("Linux视频文件不存在或删除失败：{}", videoLocalPath);
        }

        File thumbnailFile = new File(thumbnailLocalPath);
        if (thumbnailFile.exists() && thumbnailFile.delete()) {
            log.info("删除Linux封面文件成功：{}", thumbnailLocalPath);
        } else {
            log.warn("Linux封面文件不存在或删除失败：{}", thumbnailLocalPath);
        }

        // 删除数据库记录
        videoMapper.deleteVideoById(id);
        log.info("删除视频完成：ID={}", id);
    }

    @Override
    public void updateVideoById(Video video) {
        if (video == null || video.getId() == null || video.getId() <= 0) {
            log.warn("更新视频失败：参数无效");
            return;
        }
        videoMapper.updateVideoById(video);
        log.info("更新视频成功：ID={}", video.getId());
    }

    // 工具方法：提取文件后缀
    private String getFileSuffix(String originalFilename) {
        if (!StringUtils.hasText(originalFilename) || !originalFilename.contains(".")) {
            throw new IllegalArgumentException("文件格式无效：" + originalFilename);
        }
        return originalFilename.substring(originalFilename.lastIndexOf("."));
    }

    // 工具方法：统一写入文件
    private void writeFile(MultipartFile file, File destFile, String fileType) throws Exception {
        try (InputStream in = file.getInputStream();
             OutputStream out = new FileOutputStream(destFile)) {
            byte[] buffer = new byte[1024 * 1024]; // 1MB缓冲区
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.flush();
            log.info("{}文件写入Linux成功，大小：{}KB", fileType, destFile.length() / 1024);
        } catch (Exception e) {
            log.error("{}文件写入Linux失败", fileType, e);
            throw new Exception(fileType + "上传失败：" + e.getMessage());
        }
    }

    // 检查文件是否存在
    public boolean checkFileExists(String relativePath) {
        String localPath = fileStorageConfig.getLocalRootPath() + relativePath;
        File file = new File(localPath);
        boolean exists = file.exists() && file.length() > 0;
        log.info("检查Linux文件：{}，存在={}", localPath, exists);
        return exists;
    }
}