// com/example/ad/service/AdService.java
package com.example.ad.service;

import com.example.ad.entity.Ad;
import java.util.List;

public interface AdService {
    void addAd(Ad ad);       // 新增广告
    List<Ad> listAllAds();   // 查询所有广告
}