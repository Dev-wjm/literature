package com.literature.service.impl;

import com.literature.entity.Permission;
import com.literature.entity.Role;
import com.literature.repository.RoleRepository;
import com.literature.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role createRole(Role role) {
        roleRepository.save(role);
        return role;
    }

    @Override
    public Role findRolesById(String id) {
        return roleRepository.findRoleById(id);
    }

    @Override
    public List<Role> findRoleByName(String roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }

    @Override
    public void updateRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Set<Permission> findPermissionsByRoleName(String roleName) {
        Role role = roleRepository.findRoleByRole(roleName);
        return role.getpIds();
    }

    @Override
    public void uncorrelationPermissions(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void correlationPermissions(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public Role findByDescription(String roleName) {
        return roleRepository.findByDescription(roleName);
    }
}
