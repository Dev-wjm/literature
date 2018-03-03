package com.literature.controller;

import com.literature.common.JsonApi;
import com.literature.entity.Permission;
import com.literature.entity.Role;
import com.literature.service.IAuthorityService;
import com.literature.service.IPermissionService;
import com.literature.service.IRoleService;
import com.literature.service.IUserService;
import com.literature.vo.AuthorityVo;
import com.literature.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/auth")
public class AuthorityController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IAuthorityService authorityService;
    @Autowired
    private IUserService userService;


    @RequestMapping("/roles")
    @ResponseBody
    public JsonApi getAllRole() {
        JsonApi api = new JsonApi();
        api.setData(roleService.findAll());
        return api;
    }

    @RequestMapping("/permission")
    @ResponseBody
    public JsonApi getPermission(String id) {
        JsonApi api = new JsonApi();
        api.setData(roleService.findRolesById(id));
        return api;
    }

    @RequestMapping("/update")
    @ResponseBody
    public JsonApi updatePermissions(@RequestBody AuthorityVo params) {
        JsonApi api = new JsonApi();
        Role role = roleService.findByDescription(params.getRolename());
        Set<Permission> pids = new HashSet();
        for (Object pid : params.getAuthority()) {
            pids.add(permissionService.findById(pid.toString()));
        }
        role.setpIds(pids);
        roleService.updateRole(role);
        return api;
    }

    @RequestMapping("/userRole")
    @ResponseBody
    public List<UserRoleVo> userRoleAll() {
        return authorityService.findAll();
    }

    @RequestMapping("/userRole/detail")
    @ResponseBody
    public UserRoleVo userRoleByUserId(String id) {
        return authorityService.findByUserId(id);
    }

    @RequestMapping("/userRole/update")
    @ResponseBody
    public JsonApi userRoleUpdate(@RequestBody UserRoleVo params) {
        JsonApi api = new JsonApi();
        authorityService.userRoleUpdate(params);
        return api;
    }

    @RequestMapping("/userRole/add")
    @ResponseBody
    public JsonApi userRoleAdd(@RequestBody UserRoleVo params) {
        JsonApi api = new JsonApi();
        authorityService.userRoleAdd(params);
        return api;
    }

    @RequestMapping("/userRole/search")
    @ResponseBody
    public List<UserRoleVo> userRoleByUsername(String username) {
        return authorityService.findByUsername(username);
    }

    @RequestMapping("/role/search")
    @ResponseBody
    public List<Role> permissionSearch(String rolename) {
        return authorityService.findByRoleName(rolename);
    }

    @RequestMapping("/user/password/compare")
    @ResponseBody
    public JsonApi comparePassword(String id, String password){
        return userService.comparePassword(id,password);
    }

    @RequestMapping("/user/password")
    @ResponseBody
    public JsonApi updatePassword(String id, String password){
        userService.updatePassword(id,password);
        return new JsonApi("修改成功");
    }


}
