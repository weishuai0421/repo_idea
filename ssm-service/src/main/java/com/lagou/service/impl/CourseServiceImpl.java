package com.lagou.service.impl;

import com.lagou.dao.CourseMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;
import com.lagou.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;



    @Override
    public List<Course> findCourseByCondition(CourseVo courseVo) {

        List<Course> courseList = courseMapper.findCourseByCondition(courseVo);
        return courseList;



    }
    //新增课程
    @Override
    public void saveCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVo);
        Date date = new Date();

        course.setCreateTime(date);
        course.setUpdateTime(date);
        course.setIsDel(0);
        courseMapper.saveCourse(course);

        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);
        teacher.setCreateTime(date);
        teacher.setUpdateTime(date);
        teacher.setCourseId(course.getId());
        teacher.setIsDel(0);
        courseMapper.saveTeacher(teacher);

    }
    //回显课程信息
    @Override
    public CourseVo findCourseById(Integer id) {
        CourseVo courseById = courseMapper.findCourseById(id);
        return courseById;
    }
    //修改课程信息
    @Override
    public void updateCourseOrTeacher(CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        Course course = new Course();
        BeanUtils.copyProperties(course,courseVo);
        Date date = new Date();
        course.setUpdateTime(date);
        courseMapper.updateCourse(course);
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(teacher,courseVo);
        teacher.setUpdateTime(date);
        teacher.setCourseId(course.getId());
        courseMapper.updateTeacher(teacher);

    }
    //修改课程状态
    @Override
    public void updateCourseStatus(int courseId, int status) {
        Course course = new Course();
        Date date = new Date();
        course.setId(courseId);
        course.setStatus(status);
        course.setUpdateTime(date);
        courseMapper.updateCourseStatus(course);
    }


}
