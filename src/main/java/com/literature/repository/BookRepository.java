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

    @Query(value = "select * from t_books where title like %:title% ",nativeQuery = true)
    List<Books> findBooksByTitle(@Param("title") String title);

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
