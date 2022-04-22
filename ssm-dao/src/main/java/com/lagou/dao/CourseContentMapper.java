package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    //根据课程id查询课程信息和对应的章节信息和课时信息
    public List<CourseSection> findCourseSectionAndLessonByid(Integer id);

    //回显章节对应的课程信息
    public Course findCourseById(Integer id);

    //新增章节信息
    public void saveSection(CourseSection courseSection);

    //修改章节信息
    public void updateSection(CourseSection courseSection);
    //修改章节状态
    public void updateSectionStatus(CourseSection courseSection);
    //新建课时信息
    public void saveLesson(CourseLesson courseLesson);
}
