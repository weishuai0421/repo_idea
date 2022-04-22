package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //多条件查询
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVo){

        List<Course> courseList = courseService.findCourseByCondition(courseVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseList);
        return responseResult;

    }
    //图片上传
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //1.判断当前文件是否为空
        if(file.isEmpty()){
            throw  new RuntimeException();
        }
        //2.获取项目部署路径
        String realPath = request.getServletContext().getRealPath("");
        System.out.println(realPath);
        String path = realPath.substring(0, realPath.indexOf("ssm-web"));
        System.out.println(path);

        //3.获取原文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);

        //4.生成新文件名
       String newFileName =  System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println(originalFilename.lastIndexOf("."));

        System.out.println(newFileName);
        //5.文件上传
        String uploadPath = path + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果目录不存在就创建目录
        if( !filePath.getParentFile().exists()){
            filePath.getParentFile().mkdir();
            System.out.println("创建目录" + filePath);
        }
        file.transferTo(filePath);
        //6.将文件名和文件路径返回进行响应
        Map<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/" + newFileName);
        ResponseResult responseResult = new ResponseResult(true,200,"图片上传成功",map);
        return responseResult;


    }

    //新增课和修改程和讲师信息
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVo) throws InvocationTargetException, IllegalAccessException {
        ResponseResult responseResult = new ResponseResult();
             if(courseVo.getId() == null){
                 //新增
                courseService.saveCourseOrTeacher(courseVo);
                 responseResult = new ResponseResult(true ,200 ,"新增成功",null);
            }else {
                 //修改
                courseService.updateCourseOrTeacher(courseVo);
                 responseResult = new ResponseResult(true ,200 ,"修改成功",null);
            }

        return responseResult;


    }

    //回显课程信息
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVo courseById = courseService.findCourseById(id);
        ResponseResult responseResult = new ResponseResult(true , 200,"响应成功",courseById);
        return responseResult;
    }

    //修改课程状态信息
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id,int status){
        courseService.updateCourseStatus(id,status);
        Map<String ,Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true , 200,"响应成功",map);
       return responseResult;
    }
}
