package com.literature.service.impl;

import com.literature.entity.Role;
import com.literature.entity.User;
import com.literature.repository.RoleRepository;
import com.literature.repository.UserRepository;
import com.literature.service.IAuthorityService;
import com.literature.service.IUserService;
import com.literature.util.IDUtil;
import com.literature.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorityService implements IAuthorityService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserRoleVo> findAll() {
        List<UserRoleVo> ulist = new ArrayList<>();

        List<User> userList = userRepository.findAll();
        for (User user: userList) {
            UserRoleVo urv = new UserRoleVo();
            urv.setId(user.getId());
            urv.setUsername(user.getUsername());
            List roles = new ArrayList();
            for (Role role: user.getRoleIds()){
                roles.add(role.getDescription());
            }
            urv.setRoles(roles);
            ulist.add(urv);
        }
        return ulist;
    }

    @Override
    public UserRoleVo findByUserId(String id) {
        UserRoleVo vo = new UserRoleVo();
        User user = userRepository.findUsersById(id);
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        List roles = new ArrayList();
        for (Role role: user.getRoleIds()){
            roles.add(role.getDescription());
        }
        vo.setRoles(roles);
        return vo;
    }

    @Override
    public void userRoleUpdate(UserRoleVo userRoleVo) {
        User user = userRepository.findUsersById(userRoleVo.getId());
        Set roleList = new HashSet();
        for (Object rolename : userRoleVo.getRoles()){
            Role role = roleRepository.findByDescription(rolename.toString());
            roleList.add(role);
        }
        user.setRoleIds(roleList);
        userRepository.save(user);
    }

    @Override
    public void userRoleAdd(UserRoleVo userRoleVo) {
        User user = new User();
        user.setId(IDUtil.getId());
        user.setPassword("1");
        user.setUsername(userRoleVo.getUsername());
        Set roleList = new HashSet();
        for (Object rolename : userRoleVo.getRoles()){
            Role role = roleRepository.findByDescription(rolename.toString());
            roleList.add(role);
        }
        user.setRoleIds(roleList);
        userRepository.save(user);
    }

    @Override
    public List<UserRoleVo> findByUsername(String username) {
        List<UserRoleVo> ulist = new ArrayList<>();
        List<User> userList = userRepository.findByUsername(username);
        for (User user: userList) {
            UserRoleVo urv = new UserRoleVo();
            urv.setId(user.getId());
            urv.setUsername(user.getUsername());
            List roles = new ArrayList();
            for (Role role: user.getRoleIds()){
                roles.add(role.getDescription());
            }
            urv.setRoles(roles);
            ulist.add(urv);
        }
        return ulist;
    }

    @Override
    public List<Role> findByRoleName(String rolename) {
        return roleRepository.findByName(rolename);
    }
}
