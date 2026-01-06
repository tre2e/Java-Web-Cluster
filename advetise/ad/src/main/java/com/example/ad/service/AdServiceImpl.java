// com/example/ad/service/AdServiceImpl.java
package com.example.ad.service;

import com.example.ad.entity.Ad;
import com.example.ad.mapper.AdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    private AdMapper adMapper;

    @Override
    public void addAd(Ad ad) {
        // 简化UUID生成逻辑（保留核心：区分图片/视频）
        String prefix = "image_";
        if ("video".equals(ad.getAdType())) {
            prefix = "video_";
        }
        ad.setAdId(prefix + UUID.randomUUID().toString().substring(0, 8));
        adMapper.insertAd(ad);
    }

    @Override
    public List<Ad> listAllAds() {
        return adMapper.listAllAds();
    }
}