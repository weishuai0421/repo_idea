package com.lagou.controller;


import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> menuList = menuService.findAllMenu();
        ResponseResult responseResult = new ResponseResult(true, 200, "显示所有菜单成功", menuList);
        return responseResult;
    }
    //回显菜单信息
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        ResponseResult responseResult = new ResponseResult();
        if(id == -1){
                //新增菜单，回显菜单信息中就不需要查询menu信息
                List<Menu> menuList = menuService.findSubMenuListByPid(-1);
                Map<String, Object> map = new HashMap<>();
                map.put("menuInfo", null);
                map.put("parentMenuList",menuList);
               responseResult = new ResponseResult(true, 200, "添加回显成功", map);

            }else{
                //修改菜单,回显菜单中需要根据传递过来的id来查询其对应的菜单信息
                Menu menu = menuService.findMenuById(id);
                List<Menu> menuList = menuService.findSubMenuListByPid(-1);
                Map<String, Object> map = new HashMap<>();
                map.put("menuInfo", menu);
                map.put("parentMenuList",menuList);
               responseResult = new ResponseResult(true, 200, "修改回显成功", map);


            }

        return responseResult;
    }
}
