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
import java.util.*;

@Controller
@RequestMapping("/api/auth")
public class AuthorityController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IAuthorityService authorityService;
    @Autowired
    private IUserService userService;

    @RequestMapping("/role/list")
    @ResponseBody
    public JsonApi userRoleAll(String username,Integer page) {
        Map map = new HashMap();
        Integer total = authorityService.findByUsername(username).size();
        List<UserRoleVo> userList =  authorityService.findUser(username,page);
        map.put("total",total);
        map.put("userList",userList);
        return new JsonApi(map);
    }

    @RequestMapping("/role/name")
    @ResponseBody
    public List<String> getRoleName(String username,Integer page) {
        List<Role> allRole = roleService.findAll();
        List<String> roleName = new ArrayList<>();
        for (Role role: allRole){
            roleName.add(role.getDescription());
        }
        return roleName;
    }

    @RequestMapping("/role/list2")
    @ResponseBody
    public JsonApi getAllRole(String name,Integer page) {
        JsonApi api = new JsonApi();
        Map map = new HashMap();
        List<Role> roleList = new ArrayList<>();
        if (null==page || page==1) {
            roleList = authorityService.findAllRole(name,0);
        }else {
            roleList = authorityService.findAllRole(name,(page-1)*10);
        }
        Integer total = roleService.findByDesc(name).size();
        map.put("total",total);
        map.put("roleList",roleList);
        api.setData(map);
        return api;
    }

    @RequestMapping("/role/update")
    @ResponseBody
    public JsonApi userRoleUpdate(@RequestBody UserRoleVo params) {
        JsonApi api = new JsonApi();
        authorityService.userRoleUpdate(params);
        return api;
    }

    @RequestMapping("/role/delete")
    @ResponseBody
    public JsonApi deleteUser(String id) {
        authorityService.deleteUser(id);
        return new JsonApi();
    }

    @RequestMapping("/permission/List")
    @ResponseBody
    public List<Permission> getPermissionList() {
        return authorityService.findPAll();
    }

    @RequestMapping("/permission/update")
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


    @RequestMapping("/permission/get")
    @ResponseBody
    public JsonApi getPermission(String id) {
        JsonApi api = new JsonApi();
        api.setData(roleService.findRolesById(id));
        return api;
    }






    @RequestMapping("/userRole/detail")
    @ResponseBody
    public UserRoleVo userRoleByUserId(String id) {
        return authorityService.findByUserId(id);
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

    @RequestMapping("/password")
    @ResponseBody
    public JsonApi updatePassword(String id, String password){
        userService.updatePassword(id,password);
        return new JsonApi(0,"修改成功");
    }


}
