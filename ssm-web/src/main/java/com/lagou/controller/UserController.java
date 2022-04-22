package com.lagou.controller;


import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){
        PageInfo pageInfo = userService.findAllUserByPage(userVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询用户信息成功", pageInfo);
        return responseResult;


    }
    //登录
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);
        if(user1 != null){
            //将用户id和access_token放到session中,想要获得session就需要request对象
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            Map<String, Object> map = new HashMap<>();
            map.put("access_token" ,access_token);
            map.put("user_id",user1.getId());
            return new ResponseResult(true,200,"登陆成功",map);


        }else {
            return new ResponseResult(true ,200,"用户名或者密码错误",null);

        }
    }

    //根据用户id查询其所拥有的所有角色信息
    @RequestMapping("findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult( true ,200,"回显用户所拥有的角色信息成功",roleList);
    }
    //分配菜单
    @RequestMapping("/userContextRole")
    public ResponseResult UserContextRole(@RequestBody UserVo userVo){
        userService.UserContextRole(userVo);
        return new ResponseResult( true ,200,"为用户分配角色成功",null);

    }
    //获取用户权限信息
    @RequestMapping("getUserPermissions")
    public ResponseResult getUserPermission(HttpServletRequest request){
        //获取请求头中的token
        String head_toke = request.getHeader("Authorization");
        System.out.println(head_toke);
        //获取session中的token
        String access_token = (String) request.getSession().getAttribute("access_token");
        System.out.println(access_token);
        if(head_toke.equals(access_token)){
            //获取用户id，调用service
            Integer userId = (Integer) request.getSession().getAttribute("user_id");
            System.out.println(userId);
            ResponseResult responseResult = userService.getUserPermissions(userId);
            return responseResult;
        }else{
            ResponseResult responseResult = new ResponseResult(false, 400, "获取用户菜单信息失败", null);

            return responseResult;

        }
    }
}
