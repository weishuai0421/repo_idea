package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


public interface CourseService {
    //多条件不定向查询课程
    public List<Course> findCourseByCondition(CourseVo courseVo);

    //新增课程信息和讲师信息
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;

    //回显课程信息
    public CourseVo findCourseById(Integer id);

    //修改课程信息
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException;
    //课程状态信息修改
    public void updateCourseStatus(int courseId,int status);

}
