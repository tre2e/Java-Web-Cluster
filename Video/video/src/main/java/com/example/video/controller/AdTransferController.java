package com.example.video.controller;

import com.example.video.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ad")
public class AdTransferController {

    @Autowired
    private RestTemplate restTemplate;

    // 你的真实广告接口地址
    private static final String AD_API_URL = "http://10.100.164.21:8082/api/ad/get";

    /**
     * 左侧广告接口（适配真实返回格式）
     */
    @GetMapping("/getLeftAd")
    public Result<Map<String, Object>> getLeftAd() {
        return getAdData(true);
    }

    /**
     * 右侧广告接口（适配真实返回格式）
     */
    @GetMapping("/getRightAd")
    public Result<Map<String, Object>> getRightAd() {
        return getAdData(false);
    }

    /**
     * 统一解析广告数据（匹配你的JSON格式）
     */
    private Result<Map<String, Object>> getAdData(boolean isLeft) {
        try {
            // 调用真实广告接口，获取完整返回
            Map<String, Object> adResponse = restTemplate.getForObject(AD_API_URL, Map.class);

            // 接口返回空 → 用默认广告
            if (adResponse == null) {
                return Result.success(isLeft ? getDefaultLeftAd() : getDefaultRightAd());
            }

            // 解析你返回的JSON结构：外层code/msg/data → 提取data里的广告字段
            Integer code = (Integer) adResponse.get("code");
            String msg = (String) adResponse.get("msg");

            // 广告接口返回成功（code=200）→ 用真实广告数据
            if (code != null && code == 200 && "success".equals(msg)) {
                Map<String, Object> adData = (Map<String, Object>) adResponse.get("data");
                // 确保返回的广告数据包含所有必要字段（即使为null）
                return Result.success(adData != null ? adData : (isLeft ? getDefaultLeftAd() : getDefaultRightAd()));
            } else {
                // 广告接口返回失败 → 用默认广告
                return Result.success(isLeft ? getDefaultLeftAd() : getDefaultRightAd());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.success(isLeft ? getDefaultLeftAd() : getDefaultRightAd());
        }
    }

    /**
     * 左侧默认广告（匹配你的返回字段：adType/adId/videoDuration/resourceUrl/adTitle）
     */
    private Map<String, Object> getDefaultLeftAd() {
        Map<String, Object> defaultAd = new HashMap<>();
        defaultAd.put("adId", "default_left");
        defaultAd.put("adType", "image");       // 与真实接口一致的字段名
        defaultAd.put("videoDuration", null);   // 保留null字段，避免前端报错
        defaultAd.put("resourceUrl", "/assets/images/default_ad.jpg"); // 前端本地默认图
        defaultAd.put("adTitle", "默认左侧广告");
        defaultAd.put("jumpUrl", "#");          // 补充jumpUrl（真实接口若没有，前端兜底）
        return defaultAd;
    }

    /**
     * 右侧默认广告（字段完全匹配）
     */
    private Map<String, Object> getDefaultRightAd() {
        Map<String, Object> defaultAd = new HashMap<>();
        defaultAd.put("adId", "default_right");
        defaultAd.put("adType", "image");
        defaultAd.put("videoDuration", null);
        defaultAd.put("resourceUrl", "/assets/images/default_ad.jpg");
        defaultAd.put("adTitle", "默认右侧广告");
        defaultAd.put("jumpUrl", "#");
        return defaultAd;
    }
}