package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PromotionAdServiceImpl implements PromotionAdService {
    @Autowired
    private PromotionAdMapper promotionAdMapper;

    //分页查询广告数据,使用pageHelper分页插件
    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo) {
        PageHelper.startPage(promotionAdVo.getCurrentPage(),promotionAdVo.getPageSize());
        System.out.println(promotionAdVo.getCurrentPage());
        System.out.println(promotionAdVo.getPageSize());

        List<PromotionAd> promotionAdList = promotionAdMapper.findAllPromotionAdByPage();
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(promotionAdList);

        return pageInfo;
    }
    //广告状态上下线
    @Override
    public void updataPromotionAdStatus(int promotionId, int status) {
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(promotionId);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());
    }
}
