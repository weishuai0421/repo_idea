package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());

        System.out.println(userVo);
        List<User> userList = userMapper.findAllUserByPage(userVo);
        for (User user : userList) {
            System.out.println(user);
        }
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }

    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        if(user1 != null && Md5.verify(user.getPassword(),"lagou",user1.getPassword())){
            return user1;
        }else{
            return null;
        }


    }


    //根据用户id查询其所拥有的所有角色信息
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> roleList = userMapper.findUserRelationRoleById(id);
        return roleList;
    }

    //为用户分配菜单
    public void UserContextRole(UserVo userVo){
        //清空关联关系
        userMapper.deleteUserContextRole(userVo.getUserId());
        User_Role_relation user_role_relation = new User_Role_relation();
        Date date = new Date();
        //遍历集合，添加关联关系
        for (Integer integer : userVo.getRoleIdList()) {
            user_role_relation.setRoleId(integer);
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.UserContextRole(user_role_relation);
        }
    }




    //获取用户权限信息
    @Override
    public ResponseResult getUserPermissions(Integer userId) {
        //根据用户id获取他所拥有的角色集合
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);
        //获取角色id保存到list集合中
        ArrayList<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
                roleIds.add(role.getId());
        }
        //根据角色id去查询父子级菜单信息
        List<UserVo> userVoList = userMapper.findSubMenuByRoleId(roleIds);
        //根据角色id查询其所拥有的资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);
        for (Resource resource : resourceList) {
            System.out.println(resource);
        }
        for (UserVo userVo : userVoList) {

            System.out.println(userVo);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",userVoList);
        map.put("resourceList",resourceList);
        ResponseResult responseResult = new ResponseResult(true, 200, "获取用户权限信息成功", map);

        return responseResult;
    }
}
