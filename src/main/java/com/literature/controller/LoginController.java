package com.literature.controller;

import com.literature.common.JsonApi;
import com.literature.entity.Permission;
import com.literature.entity.Role;
import com.literature.entity.User;
import com.literature.service.IUserService;
import com.literature.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api")
public class LoginController {

    @Autowired
    private IUserService userService;

    private static String id;

    @RequestMapping(value = "/login")
    @ResponseBody
    public JsonApi login(@RequestBody Map<String,String> params) {
        User user = userService.login(params.get("username"),params.get("password"));
        if (null!=user) {
            Map userInfo = new HashMap();
            userInfo.put("id",user.getId());
            userInfo.put("username",params.get("username"));
            List auth = new ArrayList();
            for (Role role : user.getRoleIds()){
                for(Permission p : role.getpIds()) {
                    auth.add(p.getId());
                }
            }
            userInfo.put("auth",auth);
            return new JsonApi(userInfo);
        }
        return new JsonApi(1,"用户不存在");
    }

    @RequestMapping(value = "/beforeUpload")
    @ResponseBody
    public JsonApi beforeUpload(@RequestBody Map map) {
        id = (String)map.get("id");
        return null;
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public JsonApi upload(@RequestParam(required = false) MultipartFile file,HttpServletRequest request) throws IOException {
        JsonApi api = new JsonApi();
        //获得物理路径webapp所在路径
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path="";
        if(null!=file && !file.isEmpty()){
            //生成uuid作为文件名称
            String uuid = IDUtil.getId();
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=file.getContentType();
            //获得文件后缀名称
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            path="/static/images/"+uuid+"."+imageName;
            file.transferTo(new File(pathRoot+path));
        }
        System.out.println(id);
        api.setData(path);
        return api;
    }

}
