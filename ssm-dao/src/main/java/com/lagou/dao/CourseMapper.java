package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {
    //多条件查询课程信息
    public List<Course> findCourseByCondition(CourseVo courseVo);

    //添加课程信息
    public void saveCourse(Course course);

    //添加讲师信息
    public void saveTeacher(Teacher teacher);

    //回显课程信息
    public CourseVo findCourseById(Integer id);

    //修改讲师信息
    public void updateTeacher(Teacher teacher);
    //修改课程信息
    public void updateCourse(Course course);

    //修改课程状态
    public void updateCourseStatus(Course course);
}
