package com.lagou.dao;

import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

import java.util.List;

public interface PromotionAdMapper {
    //分页查询广告信息
    public List<PromotionAd> findAllPromotionAdByPage();
}
