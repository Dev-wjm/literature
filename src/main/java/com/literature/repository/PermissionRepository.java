package com.literature.repository;

import com.literature.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,String> {

    @Query(value = "select * from sys_permissions where name like %:name%",nativeQuery = true)
    List<Permission> findByName(@Param("name") String name);

    List<Permission> findAllByAndParentId(String id);

    Permission findPermissionsById(String id);


}
