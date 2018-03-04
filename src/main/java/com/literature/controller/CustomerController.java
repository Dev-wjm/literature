package com.literature.controller;

import com.literature.common.JsonApi;
import com.literature.entity.CustomerInfo;
import com.literature.entity.User;
import com.literature.service.IAuthorityService;
import com.literature.service.ICustomerInfoService;
import com.literature.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/api/user")
public class CustomerController {

    @Autowired
    private ICustomerInfoService customerInfoService;
    @Autowired
    private IAuthorityService authorityService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<CustomerInfo> findAll() {
        return customerInfoService.findAll();
    }

    @RequestMapping(value = "/list/2")
    @ResponseBody
    public JsonApi find(String username,Integer page) {
        Map map = new HashMap();
        Integer total = customerInfoService.findByUserName(username).size();
        List<CustomerInfo> custList = new ArrayList<>();
        if (null==page || page==1){
            custList = customerInfoService.find(username,0,10);
        }else {
            custList = customerInfoService.find(username,(page-1)*10,10);
        }

        map.put("total",total);
        map.put("custList",custList);
        return new JsonApi(map);
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public CustomerInfo findById(String id) {
        return customerInfoService.findById(id);
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonApi addCust(@RequestBody CustomerInfo params) {
        if (null == params){
            return new JsonApi(1,"添加失败");
        }
        String id = IDUtil.getId();
        // default password
        String password = "1";
        params.setId(id);
        params.setPassword(password);
        params.setCreated(new Date());
        customerInfoService.addCust(params);
        // 添加到系统用户标
        User user = new User();
        user.setId(id);
        user.setUsername(params.getUsername());;
        user.setPassword(password);
        authorityService.addUser(user);
        return new JsonApi("添加成功");
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public List<CustomerInfo> findByUsername(String username) {
        return customerInfoService.findByUserName(username);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonApi updateCust(@RequestBody CustomerInfo params) {
        JsonApi api = new JsonApi();
        customerInfoService.updateCust(params);
        return api;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonApi deleteCust(String id) {
        JsonApi api = new JsonApi();
        customerInfoService.deleteCust(id);
        return api;
    }



}
