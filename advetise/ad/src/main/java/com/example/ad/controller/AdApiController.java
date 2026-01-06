// com/example/ad/controller/AdApiController.java（极简版）
package com.example.ad.controller;

import com.example.ad.common.ResultUtil;
import com.example.ad.entity.Ad;
import com.example.ad.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/ad")
public class AdApiController {
    @Autowired
    private AdService adService;

    // 终极修复：手动构建Map返回，不直接序列化Ad对象
    @GetMapping("/get")
    public Map<String, Object> getRandomAd() {
        try {
            List<Ad> adList = adService.listAllAds();
            Map<String, Object> adData = new HashMap<>();

            if (adList != null && !adList.isEmpty()) {
                Ad randomAd = adList.get(new Random().nextInt(adList.size()));
                // 手动赋值，只取需要的字段，完全避开Ad对象的序列化
                adData.put("adId", randomAd.getAdId());
                adData.put("adType", randomAd.getAdType());
                adData.put("resourceUrl", randomAd.getResourceUrl());
                adData.put("adTitle", randomAd.getAdTitle());
                adData.put("videoDuration", randomAd.getVideoDuration());
                // 注释掉createTime，避免Date类型触发递归（若需返回，手动格式化）
                // adData.put("createTime", randomAd.getCreateTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(randomAd.getCreateTime()) : null);
            }

            // 直接返回ResultUtil，此时data是纯Map，无递归风险
            return ResultUtil.success("success", adData);
        } catch (Exception e) {
            // 打印异常栈，方便定位（可选）
            e.printStackTrace();
            return ResultUtil.error("获取广告失败");
        }
    }
}