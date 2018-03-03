package com.literature.service;

import com.literature.entity.Role;
import com.literature.vo.UserRoleVo;

import java.util.List;

public interface IAuthorityService {
    List<UserRoleVo> findAll();
    UserRoleVo findByUserId(String id);
    void userRoleUpdate(UserRoleVo userRoleVo);
    void userRoleAdd(UserRoleVo userRoleVo);
    List<UserRoleVo> findByUsername(String username);

    List<Role> findByRoleName(String rolename);
}
