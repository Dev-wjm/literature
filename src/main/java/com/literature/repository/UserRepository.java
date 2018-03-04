package com.literature.repository;

import com.literature.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Query("select u from User u where u.username=?1")
    User findByUserName(String username);

    @Query(value = "SELECT  * from sys_users t WHERE (:username is null or t.username like %:username% ) limit :page , 10", nativeQuery = true)
    List<User> find(@Param("username") String username,@Param("page") Integer page);

    User findUsersById(String uid);

    @Query(value = "select * from sys_users where (:username is null or username like %:username% )",nativeQuery = true)
    List<User> findByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "update sys_users set password=:password where id=:id",nativeQuery = true)
    void updatePassword(@Param("id") String id, @Param("password") String password);

    @Query(value = "select password from sys_users where id=:id", nativeQuery = true)
    String getPasswordById(@Param("id") String id);

    @Query(value = "select * from sys_users where username=:username and password=:password", nativeQuery = true)
    User login(@Param("username") String username, @Param("password") String password);
}
