package com.lagou.controller;

import com.lagou.domain.Test;
import com.lagou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.List;
@RestController    //相当于@RequestBody和@Controller
@RequestMapping(value = "/test")
public class TestController {



    @Autowired
   private TestService testService;


    @RequestMapping("/findAll")
    public List<Test> findAll(){

     return null;
    }
}
