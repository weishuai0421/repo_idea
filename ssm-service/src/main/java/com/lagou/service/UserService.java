package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {
    //多条件分页查询用户信息
    public PageInfo findAllUserByPage(UserVo userVo);
    //登录
    public User login(User user) throws Exception;
    //根据用户id查询其所拥有的所有角色信息
    public List<Role> findUserRelationRoleById(Integer id);

    //为用户分配角色
    public void UserContextRole(UserVo userVo);

    //获取用户权限进行动态菜单展示
    public ResponseResult getUserPermissions(Integer userId);
}
