package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    //多条件分页查询用户信息
    public List<User> findAllUserByPage(UserVo userVo);
    //根据手机号查询用户信息
    public User login(User user);



    //分配角色的方法，需要两个方法来进行实现，分别是删除中间表关联关系的方法，和为中间表插入数据的方法
    //删除中间表关联关系的方法
    public void deleteUserContextRole(Integer id);
    //分配角色，即为中间表插入数据的方法
    public void UserContextRole(User_Role_relation user_role_relation);

    //根据用户id查询其所用的角色信息
    public List<Role> findUserRelationRoleById(Integer id);
    //根据角色id查询其所拥有的父子级菜单信息
    public List<UserVo> findSubMenuByRoleId(List<Integer> roleIds);

    //根据用户id(通过角色id)查询其所拥有的资源信息
    public List<Resource> findResourceByRoleId(List<Integer> roleIds);
}
