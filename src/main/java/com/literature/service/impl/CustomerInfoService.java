package com.literature.service.impl;

import com.literature.common.JsonApi;
import com.literature.entity.CustomerInfo;
import com.literature.entity.User;
import com.literature.repository.CustomerInfoRepository;
import com.literature.repository.UserRepository;
import com.literature.service.ICustomerInfoService;
import com.literature.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CustomerInfoService implements ICustomerInfoService {

    @Autowired
    private CustomerInfoRepository customerInfoRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public JsonApi register(CustomerInfo users) {
        String id = IDUtil.getId();
        users.setId(id);
        users.setCreated(new Date());
        User u = new User();
        // 关联角色用户
        u.setId(id);
        u.setUsername(users.getUsername());
        u.setPassword(users.getPassword());
        try {
            customerInfoRepository.save(users);
            userRepository.save(u);
        }catch (Exception e) {
            return new JsonApi("fail");
        }
        JsonApi api = new JsonApi();
        api.setData(users);
        return api;
    }

    @Override
    public List<CustomerInfo> findAll() {
        return customerInfoRepository.findAll();
    }

    @Override
    public CustomerInfo findById(String id) {
        return customerInfoRepository.findCustomerInfoById(id);
    }

    @Override
    public void addCust(CustomerInfo cust) {
        customerInfoRepository.save(cust);
    }

    @Override
    public List<CustomerInfo> findByUserName(String username) {
        return customerInfoRepository.findByUsername(username);
    }

    @Override
    public void updateCust(CustomerInfo cust) {
        customerInfoRepository.save(cust);
    }

    @Override
    public void deleteCust(String id) {
        customerInfoRepository.deleteById(id);
    }

    @Override
    public List<CustomerInfo> find(String username, Integer start, Integer end) {
        return customerInfoRepository.find(username,start,end);
    }
}
