package com.literature.controller;

import com.literature.common.JsonApi;
import com.literature.entity.CustomerInfo;
import com.literature.service.ICustomerInfoService;
import com.literature.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class CustomerController {

    @Autowired
    private ICustomerInfoService customerInfoService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<CustomerInfo> findAll() {
        return customerInfoService.findAll();
    }

    @RequestMapping(value = "/detail")
    @ResponseBody
    public CustomerInfo findById(String id) {
        return customerInfoService.findById(id);
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonApi addCust(@RequestBody CustomerInfo params) {
        JsonApi api = new JsonApi();
        String id = IDUtil.getId();
        // default password
        String password = "1";
        params.setId(id);
        params.setPassword(password);
        params.setCreated(new Date());
        customerInfoService.addCust(params);
        return api;
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
