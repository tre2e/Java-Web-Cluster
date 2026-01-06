// com/example/ad/mapper/AdMapper.java
package com.example.ad.mapper;

import com.example.ad.entity.Ad;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface AdMapper {
    void insertAd(Ad ad);      // 新增广告
    List<Ad> listAllAds();     // 查询所有广告
}