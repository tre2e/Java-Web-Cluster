// com/example/ad/controller/AdController.java
package com.example.ad.controller;

import com.example.ad.common.ResultUtil;
import com.example.ad.entity.Ad;
import com.example.ad.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 示例：新增广告的Controller
@RestController
@RequestMapping("/api/admin/ad")
public class AdController {
    @Autowired
    private AdService adService;

    @PostMapping("/add")
    public Map<String, Object> addAd(@RequestBody Ad ad) {
        try {
            adService.addAd(ad);
            // 返回纯Map，不返回Ad对象
            Map<String, Object> resultData = new HashMap<>();
            resultData.put("msg", "新增成功");
            resultData.put("adId", ad.getAdId());
            return ResultUtil.success("新增广告成功", resultData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("新增广告失败");
        }
    }

    @GetMapping("/list")
    public Map<String, Object> listAllAds() {
        try {
            List<Ad> adList = adService.listAllAds();
            // 手动转换List<Ad>为List<Map>，避开序列化
            List<Map<String, Object>> adMapList = new ArrayList<>();
            for (Ad ad : adList) {
                Map<String, Object> adMap = new HashMap<>();
                adMap.put("adId", ad.getAdId());
                adMap.put("adType", ad.getAdType());
                adMap.put("resourceUrl", ad.getResourceUrl());
                adMap.put("adTitle", ad.getAdTitle());
                adMap.put("videoDuration", ad.getVideoDuration());
                adMapList.add(adMap);
            }
            return ResultUtil.success("查询成功", adMapList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("查询失败");
        }
    }
}