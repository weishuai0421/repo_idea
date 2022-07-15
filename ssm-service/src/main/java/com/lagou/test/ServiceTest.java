package com.lagou.test;

import com.github.pagehelper.PageInfo;
import com.lagou.dao.CourseMapper;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.CourseService;
import com.lagou.service.TestService;
import com.lagou.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value ="classpath:applicationContext-service.xml" )
public class ServiceTest {
    @Autowired
    private TestService testService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;







    @Test
    public void test02(){
        System.out.println("测试");
        CourseVo courseVo = new CourseVo();
        System.out.println(courseVo);
        System.out.println("测试1");

        List<Course> courseList = courseService.findCourseByCondition(courseVo);
        //List<Course> courseList = courseMapper.findCourseByCondition(courseVo);
        System.out.println("看看到了那一步");
        System.out.println(courseList);
    }


    public void test04(){
        System.out.println("测试11");
    }


    @Test
    public void test03(){
        System.out.println("看看会不会卡死");
        test04();
    }
    @Test
    public void test05(){
        ResponseResult userPermissions = userService.getUserPermissions(100030011);

    }
    @Test
    public void test06(){
        UserVo userVo = new UserVo();
        userVo.setCurrentPage(1);
        userVo.setPageSize(10);
        userVo.setUsername("15321919577");
        System.out.println(userVo);
//        List<User> userList = userMapper.findAllUserByPage(userVo);
//        for (User user : userList) {
//            System.out.println(user);
//        }
        PageInfo pageInfo = userService.findAllUserByPage(userVo);
         System.out.println(pageInfo.toString());
//        List<User> userList = userMapper.findAllUserByPage(userVo);
//        for (User user : userList) {
//            System.out.println(user);
//        }

    }
}
