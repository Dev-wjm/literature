package com.literature.service.impl;

import com.literature.common.JsonApi;
import com.literature.entity.Permission;
import com.literature.entity.Role;
import com.literature.entity.User;
import com.literature.repository.RoleRepository;
import com.literature.repository.UserRepository;
import com.literature.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findById(String id) {
        return userRepository.findUsersById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUserName(String username) {
//        userRepository.findByUserName(username);
        return userRepository.findByUserName(username);
    }

    @Override
    public void changePassword(String id, String newPassword) {

    }

    @Override
    public void correlationRoles(User user) {
        userRepository.save(user);
    }

    @Override
    public void uncorrelationRoles(User user) {
        userRepository.save(user);
    }

    @Override
    public Set<Role> findRolesByUsername(String username) {
        User user = userRepository.findByUserName(username);
        return user.getRoleIds();
    }

    @Override
    public Set<Permission> findPermissions(String uid) {
        User user = userRepository.findUsersById(uid);
        Set<Permission> pset = new HashSet<>();
        for (Role role:user.getRoleIds()){
            pset.addAll(role.getpIds());
        }
        return pset;
    }

    @Override
    public void updatePassword(String id, String newpassword) {
        userRepository.updatePassword(id,newpassword);
    }

    @Override
    public JsonApi comparePassword(String id, String password) {
        String comfirm = userRepository.getPasswordById(id);
        if(!StringUtils.isEmpty(password) || comfirm.equals(password)) {
            return new JsonApi();
        }
        return new JsonApi(1,"密码错误");
    }

    @Override
    public User login(String username, String password) {
        return userRepository.login(username,password);
    }
}
