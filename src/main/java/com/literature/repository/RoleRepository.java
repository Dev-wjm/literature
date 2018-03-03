package com.literature.repository;

import com.literature.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {

    @Query(value = "select * from sys_roles where role_name like %:name%",nativeQuery = true)
    List<Role> findRoleByRoleName(@Param("name") String role);

    Role findRoleByRole(String role);

    Role findRoleById(String id);

    Role findByDescription(String roleName);

    @Query(value = "SELECT * from sys_roles WHERE description in (?1)",nativeQuery = true)
    List<Role> findByRoleName(String roleName);

    @Query(value = "select * from sys_roles where description like %:roleName% ", nativeQuery = true)
    List<Role> findByName(@Param("roleName") String roleName);

}
