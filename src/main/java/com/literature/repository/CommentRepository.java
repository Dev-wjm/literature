package com.literature.repository;

import com.literature.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments,String> {

    List<Comments> findByBookId(String bookId);

    List<Comments> findByUserId(String userId);

    Comments findCommentsById(String id);



}
