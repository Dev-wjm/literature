package com.literature.service;

import com.literature.common.JsonApi;
import com.literature.entity.CustomerInfo;

import java.util.List;

public interface ICustomerInfoService {
    JsonApi register(CustomerInfo users);

    List<CustomerInfo> findAll();

    CustomerInfo findById(String id);

    void addCust(CustomerInfo cust);

    List<CustomerInfo> findByUserName(String username);

    void updateCust(CustomerInfo cust);

    void deleteCust(String id);
}
