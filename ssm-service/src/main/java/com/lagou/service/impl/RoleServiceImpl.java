package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    //查询所有角色
    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> roleList = roleMapper.findAllRole(role);
        return roleList;
    }
    //根据角色id查询所拥有的菜单id
    @Override
    public List<Integer> findAllMenuByRoleId(Integer roleId) {
        List<Integer> integerList = roleMapper.findAllMenuByRoleId(roleId);
        return integerList;
    }
    //为角色分配菜单
    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        //清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
        //分配菜单信息
        Role_menu_relation role_menu_relation = new Role_menu_relation();
        Date date = new Date();
        for (Integer integer : roleMenuVo.getMenuIdList()) {
            role_menu_relation.setMenuId(integer);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            roleMapper.roleContextMenu(role_menu_relation);
        }
    }
    //删除用户
    @Override
    public void deleteRole(Integer rid) {
        roleMapper.deleteRole(rid);
        roleMapper.deleteRoleContextMenu(rid);
    }


}
