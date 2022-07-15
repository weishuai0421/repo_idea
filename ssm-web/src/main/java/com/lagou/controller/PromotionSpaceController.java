package com.lagou.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import com.mysql.fabric.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;
    //查询所有广告位的方法
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> promotionSpaceList = promotionSpaceService.findAllPromotionSpace();
        ResponseResult responseResult = new ResponseResult(true, 200, "查询广告位成功", promotionSpaceList);
        return responseResult;

    }
    //添加或修改广告位的方法
    @RequestMapping("/saveOrUpdatePromotionSpace")
//    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody String promotionSpace) throws IOException {
//        System.out.println(promotionSpace);
//        ObjectMapper objectMapper = new ObjectMapper();
//        PromotionSpace promotionSpace1 = objectMapper.readValue(promotionSpace, PromotionSpace.class);
//        promotionSpaceService.savePromotionSpace(promotionSpace1);
//        ResponseResult responseResult = new ResponseResult(true, 200, "新增广告位成功", null);
//
//
//        return responseResult;
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody Map map){
        PromotionSpace promotionSpace = new PromotionSpace();
        promotionSpace.setName((String) map.get("name"));
       promotionSpace.setId((Integer) map.get("id"));
       ResponseResult responseResult = new ResponseResult();
       if(promotionSpace.getId() == null){
           //新增广告位
           promotionSpaceService.savePromotionSpace(promotionSpace);
            responseResult = new ResponseResult(true, 200, "新增广告位成功", null);

       }else{
           //修改广告位
           promotionSpaceService.updatePromotionSpace(promotionSpace);
           responseResult = new ResponseResult(true, 200, "修改广告位成功", null);
       }
        return responseResult;
        }

        //根据id查询广告位信息，回显广告位名称
        @RequestMapping("/findPromotionSpaceById")
        public ResponseResult findPromotionSpaceById(Integer id){
            PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);
            ResponseResult responseResult = new ResponseResult(true, 200, "回显广告位成功", promotionSpace);
            return responseResult;
        }

    }

