package com.example.ad.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class Ad {
    private Integer id;
    private String adId;       // 广告唯一标识
    private String adType;     // image/video
    private String resourceUrl;// 资源URL
    private String adTitle;    // 广告标题
    private Integer videoDuration; // 视频时长

    // 核心修复：格式化Date类型，避免Jackson递归序列化
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JsonProperty("createTime") // 明确JSON字段名，避免序列化歧义
    private Date createTime;   // 创建时间

    // 空构造
    public Ad() {}

    // 所有getter/setter保持不变
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getAdId() { return adId; }
    public void setAdId(String adId) { this.adId = adId; }
    public String getAdType() { return adType; }
    public void setAdType(String adType) { this.adType = adType; }
    public String getResourceUrl() { return resourceUrl; }
    public void setResourceUrl(String resourceUrl) { this.resourceUrl = resourceUrl; }
    public String getAdTitle() { return adTitle; }
    public void setAdTitle(String adTitle) { this.adTitle = adTitle; }
    public Integer getVideoDuration() { return videoDuration; }
    public void setVideoDuration(Integer videoDuration) { this.videoDuration = videoDuration; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}