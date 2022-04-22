package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    //���ݿγ�id��ѯ�γ���Ϣ�Ͷ�Ӧ���½���Ϣ�Ϳ�ʱ��Ϣ
    public List<CourseSection> findCourseSectionAndLessonByid(Integer id);

    //�����½ڶ�Ӧ�Ŀγ���Ϣ
    public Course findCourseById(Integer id);

    //�����½���Ϣ
    public void saveSection(CourseSection courseSection);

    //�޸��½���Ϣ
    public void updateSection(CourseSection courseSection);
    //�޸��½�״̬
    public void updateSectionStatus(CourseSection courseSection);
    //�½���ʱ��Ϣ
    public void saveLesson(CourseLesson courseLesson);
}
