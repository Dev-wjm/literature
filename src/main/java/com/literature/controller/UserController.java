package com.literature.controller;

import com.literature.common.JsonApi;
import com.literature.entity.CustomerInfo;
import com.literature.service.ICustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private ICustomerInfoService customerInfoService;

    @RequestMapping("/registers")
    @ResponseBody
    public JsonApi login(@RequestBody CustomerInfo params) {
        return customerInfoService.register(params);
    }

//    @RequestMapping("/login")
//    public String login(String username,String password) {
//        JsonApi data = new JsonApi();
//        return "admin/index";
//    }


}
