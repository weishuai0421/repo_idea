package com.lagou.service;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ResourceCategoryService{
    //查询所有资源分类信息
    public List<ResourceCategory> findAllResourceCategory();
};



