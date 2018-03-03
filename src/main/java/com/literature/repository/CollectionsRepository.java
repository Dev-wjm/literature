package com.literature.repository;


import com.literature.entity.Collections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectionsRepository extends JpaRepository<Collections, String> {

    @Query("select u.bookId from Collections u where u.userId=:userId ")
    List<String> getBooksId(@Param("userId") String userId);

    @Query("select u.userId from Collections u where u.bookId=:bookId ")
    List<String> getUsersId(@Param("bookId") String bookId);

    Collections getByUserIdAndBookId(@Param("userId") String userId, @Param("bookId") String bookId);
}
