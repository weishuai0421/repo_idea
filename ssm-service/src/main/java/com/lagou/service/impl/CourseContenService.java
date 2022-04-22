package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseContenService implements CourseContentService {
    @Autowired
    private CourseContentMapper courseContentMapper;
    @Override
    public List<CourseSection> findCourseSectionAndLessonByid(Integer id) {
        List<CourseSection> courseSectionList = courseContentMapper.findCourseSectionAndLessonByid(id);

        return courseSectionList;
    }
    //回显章节对应的课程信息
    @Override
    public Course findCourseById(Integer id) {
        Course course = courseContentMapper.findCourseById(id);
        return course;
    }
    //新增章节信息
    @Override
    public void saveSection(CourseSection courseSection) {
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        courseContentMapper.saveSection(courseSection);
    }
    //修改章节信息
    @Override
    public void updateSection(CourseSection courseSection) {
        Date date = new Date();

        courseSection.setUpdateTime(date);

        courseContentMapper.updateSection(courseSection);
    }
    //修改章节状态
    @Override
    public void updateSectionStatus(Integer id, Integer status) {
        CourseSection courseSection = new CourseSection();
        Date date = new Date();
        courseSection.setId(id);
        courseSection.setUpdateTime(date);
        courseSection.setStatus(status);
        courseContentMapper.updateSectionStatus(courseSection);
    }
    //新增课时信息
    @Override
    public void saveLesson(CourseLesson courseLesson) {
        //补全信息
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);
        courseContentMapper.saveLesson(courseLesson);
    }
}
