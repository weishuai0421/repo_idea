package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {
    //多条件查询资源信息
    public List<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
