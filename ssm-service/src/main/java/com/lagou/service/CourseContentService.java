package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    //根据课程id查询课程信息和章节信息，课时信息
    public List<CourseSection> findCourseSectionAndLessonByid(Integer id);
    //回显章节对应的课程信息
    public Course findCourseById(Integer id);

    //新增章节信息
    public void saveSection(CourseSection courseSection);

    //修改章节信息
    public void updateSection(CourseSection courseSection);

    //修改章节状态
    public void updateSectionStatus(Integer id,Integer status);

    //新增课时信息
    public void saveLesson(CourseLesson courseLesson);
}
