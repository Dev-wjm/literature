package com.literature.repository;

import com.literature.entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo,String> {

    CustomerInfo findCustomerInfoById(String id);

    @Query(value = "select * from t_customerInfo t where (:username is null or t.user_name like %:username%) ",nativeQuery = true)
    List<CustomerInfo> findByUsername(@Param("username") String username);

    void deleteById(String id);

    @Query(value = "SELECT * FROM  t_customerInfo t where ( :username is null or t.user_name like %:username% ) limit :start , :ends",nativeQuery = true)
    List<CustomerInfo> find(@Param("username") String username,@Param("start")Integer start,@Param("ends")Integer end);
}
