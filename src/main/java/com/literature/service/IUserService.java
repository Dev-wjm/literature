package com.literature.service;


import com.literature.common.JsonApi;
import com.literature.entity.Permission;
import com.literature.entity.Role;
import com.literature.entity.User;

import java.util.List;
import java.util.Set;

public interface IUserService {

    User findById(String id);

    List<User> findAll();

    /**
     * 创建用户
     * @param user
     * @return
     */
    User createUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(String id);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUserName(String username);

    /**
     * 修改密码
     * @param id
     * @param newPassword
     */
    void changePassword(String id, String newPassword);

    /**
     * 添加 用户-角色 关系
     * @param user
     */
    void correlationRoles(User user);

    /**
     * 删除 用户-角色 关系
     * @param user
     */
    void uncorrelationRoles(User user);


    /**
     * 根据用户查找角色列表
     * @param username
     * @return
     */
    Set<Role> findRolesByUsername(String username);

    /**
     * 根据用户查找权限列表
     * @param uid
     * @return
     */
    Set<Permission> findPermissions(String uid);

    void updatePassword(String id, String newpassword);

    JsonApi comparePassword(String id,String password);

    User login(String username,String password);

}
