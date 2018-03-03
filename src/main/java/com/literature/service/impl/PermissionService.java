package com.literature.service.impl;

import com.literature.entity.Permission;
import com.literature.repository.PermissionRepository;
import com.literature.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission createPermission(Permission permission) {
        permissionRepository.save(permission);
        return permission;
    }

    @Override
    public Permission findById(String id) {
        return permissionRepository.findPermissionsById(id);
    }

    @Override
    public void deletePermission(String id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionRepository.saveAndFlush(permission);
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public List<Permission> findByName(String name) {
        return permissionRepository.findByName(name);
    }

    @Override
    public List<Permission> findChildByPid(String pid) {
        return permissionRepository.findAllByAndParentId(pid);
    }
}
