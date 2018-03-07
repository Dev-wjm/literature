package com.literature.repository;

import com.literature.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comments,String> {

    @Query(value = "select * from t_comment t where (:bookName is null or t.title like %:bookName% ) limit :page , :sizes", nativeQuery = true)
    List<Comments> find(@Param("bookName") String bookName, @Param("page")Integer page,@Param("sizes")Integer sizes);

    @Query(value = "select * from t_comment t where (:bookName is null or t.title like %:bookName% )", nativeQuery = true)
    List<Comments> findByTitle(@Param("bookName") String bookName);

    @Query(value = "select * from t_comment t where t.user_id = :id AND (:bookName is null or t.title like %:bookName% ) limit :page , :sizes", nativeQuery = true)
    List<Comments> findCust(@Param("bookName") String bookName,@Param("id")String id, @Param("page")Integer page,@Param("sizes")Integer sizes);

    @Query(value = "select * from t_comment t where t.user_id = :id AND (:bookName is null or t.title like %:bookName% )", nativeQuery = true)
    List<Comments> findCustByTitle(@Param("bookName") String bookName,@Param("id")String id);

    @Query(value = "select * from t_comment t where t.book_id = :id limit :page , 10", nativeQuery = true)
    List<Comments> findCommentsByBookId(@Param("id") String id,@Param("page") Integer page);

    List<Comments> findByBookId(String bookId);

    List<Comments> findByUserId(String userId);

    Comments findCommentsById(String id);



}
