package com.literature.repository;


import com.literature.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books,String> {

    Books findBooksById(String id);

    @Query(value = "select * from t_books t where (:title is null or t.title like %:title% ) ",nativeQuery = true)
    List<Books> findBooksByTitle(@Param("title") String title);

    @Query(value = "select * from t_books t where (:title is null or t.title like %:title% ) limit :page,:sizes",nativeQuery = true)
    List<Books> find(@Param("title")String title,@Param("page")Integer page,@Param("sizes")Integer sizes);

    @Query(value = "SELECT * FROM t_books b LEFT JOIN t_collections c on b.id = c.book_id WHERE c.user_id = :id AND (:title is null or b.title like %:title%)",nativeQuery = true)
    List<Books> findCustTitle(@Param("title")String title,@Param("id")String id);

    @Query(value = "SELECT * FROM t_books b LEFT JOIN t_collections c on b.id = c.book_id WHERE c.user_id = :id AND (:title is null or b.title like %:title%) limit :page,10", nativeQuery = true)
    List<Books> findCustByUserid(@Param("title")String title,@Param("id") String id, @Param("page") Integer page);

    Books findBooksByAuthorIs(String author);

    Books findBooksByIbsn(String ibsn);

    List<Books> findBooksByAuthor(String author);

    @Query("select u.tags from Books u where u.id=:bookId")
    String findTags(@Param(value = "bookId") String bookId);

    @Modifying
    @Query("update Books u set u.tags=:tags where u.id=:bookId")
    void addTags(@Param(value = "tags") String tag, @Param(value = "bookId") String bookId);

    @Modifying
    @Query("update Books u set u.collection=u.collection+1 where u.id=:bookId")
    void addCollection(@Param(value = "bookId") String bookId);

    @Modifying
    @Query("update Books u set u.collection=u.collection-1 where u.id=:bookId")
    void cancelCollection(@Param(value = "bookId") String bookId);

}
