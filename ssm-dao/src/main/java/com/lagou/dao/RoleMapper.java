package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import javax.print.DocFlavor;
import java.util.List;

public interface RoleMapper {
    //查询所有角色，（条件）
    public List<Role> findAllRole(Role role);

    //根据角色id查询其所拥有的菜单id
    public List<Integer> findAllMenuByRoleId(Integer roleId);

    //根据roleId清空关联关系
    public void deleteRoleContextMenu(int rid);
    //根据roleId添加上关联关系
    public void roleContextMenu(Role_menu_relation role_menu_relation);
    //删除角色
    public void deleteRole(Integer rid);
}
