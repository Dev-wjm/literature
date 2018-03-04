package com.literature.service;

import com.literature.entity.Permission;
import com.literature.entity.Role;
import com.literature.entity.User;
import com.literature.vo.UserRoleVo;

import java.util.List;

public interface IAuthorityService {
    List<UserRoleVo> findUser(String username,Integer page);
    List<UserRoleVo> findByUsername(String username);
    void deleteUser(String id);
    void addUser(User user);

    List<Role> findAllRole(String name,Integer page);

    UserRoleVo findByUserId(String id);
    void userRoleUpdate(UserRoleVo userRoleVo);
    void userRoleAdd(UserRoleVo userRoleVo);


    List<Role> findByRoleName(String rolename);

    List<Permission> findPAll();
}
