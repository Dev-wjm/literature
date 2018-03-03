package com.literature.service;

import com.literature.entity.Permission;

import java.util.List;


public interface IPermissionService {

    /**
     * 创建权限
     * @param permission
     * @return
     */
    Permission createPermission(Permission permission);

    Permission findById(String id);

    /**
     * 删除权限
     * @param id
     */
    void deletePermission(String id);

    /**
     * 更新权限
     * @param permission
     */
    void updatePermission(Permission permission);

    /**
     * 查看所有权限
     * @return
     */
    List<Permission> findAll();

    /**
     * 根据权限名查找权限
     * @param name
     * @return
     */
    List<Permission> findByName(String name);

    /**
     * 查找子级权限
     * @param pid
     * @return
     */
    List<Permission> findChildByPid(String pid);

}
