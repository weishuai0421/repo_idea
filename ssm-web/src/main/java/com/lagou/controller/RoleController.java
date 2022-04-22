package com.lagou.controller;


import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有角色成功", allRole);
        return responseResult;
    }

    @Autowired
    private MenuService menuService;
    @RequestMapping("/findAllMenu")
    //查询所有菜单信息
    public ResponseResult findAllMenu(){
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);
        Map<String ,List> map = new HashMap<>();
        map.put("parentMenuList",menuList);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有菜单成功", map);
        return  responseResult;
    }
    @RequestMapping("/findMenuByRoleId")
    //根据角色id查询其所拥有的菜单id
    public ResponseResult findAllMenuByRoleId(Integer roleId){
        List<Integer> integerList = roleService.findAllMenuByRoleId(roleId);
        ResponseResult responseResult = new ResponseResult(true, 200, "查询用户所拥有的菜单id成功", integerList);
        return responseResult;
    }
    //为角色分配菜单信息
    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "为角色分配菜单成功", "");
        return responseResult;


    }
    //删除角色
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色成功", "");
        return responseResult;
    }
}
