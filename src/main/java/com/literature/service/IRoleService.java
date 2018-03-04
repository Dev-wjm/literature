package com.literature.service;

import com.literature.entity.Permission;
import com.literature.entity.Role;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface IRoleService {

    /**
     * 创建角色
     * @param role
     * @return
     */
    Role createRole(Role role);

    Role findRolesById(String id);

    /**
     * 根据角色名字查找角色
     * @param roleName
     * @return
     */
    List<Role> findByDesc(String roleName);

    /**
     * 查找所有角色
     * @return
     */
    List<Role> findAll();

    /**
     * 删除角色
     * @param id
     */
    void deleteRole(String id);

    /**
     * 更新角色
     * @param role
     */
    void updateRole(Role role);

    /**
     * 根据角色查找权限列表
     * @param roleName
     * @return
     */
    Set<Permission> findPermissionsByRoleName(String roleName);

    /**
     * 删除 角色-权限 关系
     * @param role
     */
    void uncorrelationPermissions(Role role);

    /**
     * 创建 角色-权限 关系
     * @param role
     */
    void correlationPermissions(Role role);

    List<Role> findByRoleName(String roleName);

    Role findByDescription(String roleName);
}
