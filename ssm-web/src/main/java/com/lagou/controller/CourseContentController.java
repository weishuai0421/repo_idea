package com.lagou.controller;


import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController//相当于@Controller和@ResponseBody
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findCourseSectionAndLesson")
    public ResponseResult findCourseSectionAndLessonById(Integer courseId){
        List<CourseSection> courseSectionList = courseContentService.findCourseSectionAndLessonByid(courseId);
        ResponseResult responseResult = new ResponseResult(true ,200,"章节和课时内容查询成功",courseSectionList);
        return responseResult;
    }
    //回显章节对应的课程信息
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer courseId){
        Course course = courseContentService.findCourseById(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "回显课程信息成功", course);
        return responseResult;
    }
    //新增章节信息
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        ResponseResult responseResult = new ResponseResult();

        //判断是否携带了章节id
        if(null != courseSection.getId()){

            //修改操作
            courseContentService.updateSection(courseSection);
            responseResult = new ResponseResult(true, 200, "修改章节成功", null);
        }else {
            //新增操作
            courseContentService.saveSection(courseSection);
           responseResult = new ResponseResult(true, 200, "新增章节成功", null);
        }
        return responseResult;
    }
    //修改章节状态
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(Integer id, Integer status){
        courseContentService.updateSectionStatus(id,status);
        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "修改课程状态成功", map);
        return responseResult;
    }
    //新增课时
    @RequestMapping("saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson){
        courseContentService.saveLesson(courseLesson);
        ResponseResult responseResult = new ResponseResult(true, 200, "新增课时成功", null);
        return responseResult;
    }
}
