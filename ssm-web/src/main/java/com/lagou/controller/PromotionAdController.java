package com.lagou.controller;


import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService promotionAdService;
    //广告分页查询
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo promotionAdVo){
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "广告分页查询成功", pageInfo);
        return responseResult;
    }
    //图片上传接口代码复用
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断当前文件是否为空
        if(file.isEmpty()){
            throw  new RuntimeException();
        }
        //2.获取项目部署路径
        String realPath = request.getServletContext().getRealPath("");
        System.out.println(realPath);
        String path = realPath.substring(0, realPath.indexOf("ssm-web"));
        System.out.println(path);

        //3.获取原文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);

        //4.生成新文件名
        String newFileName =  System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println(originalFilename.lastIndexOf("."));

        System.out.println(newFileName);
        //5.文件上传
        String uploadPath = path + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果目录不存在就创建目录
        if( !filePath.getParentFile().exists()){
            filePath.getParentFile().mkdir();
            System.out.println("创建目录" + filePath);
        }
        file.transferTo(filePath);
        //6.将文件名和文件路径返回进行响应
        Map<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/" + newFileName);
        ResponseResult responseResult = new ResponseResult(true,200,"图片上传成功",map);
        return responseResult;
        //广告上下线状态的切换






    }

}
