package com.lagou.service.impl;

import com.lagou.dao.TestMapper;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Test;
import com.lagou.service.TestService;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestServiceImp implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Autowired
    private UserService userService;

    @Override
    public void findAll() {

    }
}

