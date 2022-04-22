package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {
    //查询所有角色（条件）
    public List<Role> findAllRole(Role role);
    //根据角色id查询其所拥有的菜单id
    public List<Integer> findAllMenuByRoleId(Integer roleId);

    //为角色分配菜单
    public void roleContextMenu(RoleMenuVo roleMenuVo);

    //删除角色，注意删除角色时，需要将关系表中对应roleID的关联关系删除
    public void deleteRole(Integer rid);
}
