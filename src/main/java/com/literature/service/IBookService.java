package com.literature.service;

import com.literature.entity.Books;
import com.literature.entity.Comments;
import com.literature.entity.Nominate;

import java.util.List;
import java.util.Map;

public interface IBookService {

    List<Books> find(String title,Integer page,Integer size);
    void addBook(Books book);
    void deleteById(String id);
    Books findById(String id);
    void updateBook(Books books);
    List<Books> findBooksByTitle(String title);


    List<Comments> findComment(String title,Integer page,Integer size);
    List<Comments> findCommentByTitle(String title);
    List<Comments> findCommentAll();
    Comments findComnentById(String id);
    void deleteCommentById(String id);

    // 推荐设置
    void setNominate(Nominate nominate);

    // 个人用户查询
    List<Comments> findCustComment(String title,String id,Integer page,Integer size);
    List<Comments> findCustCommentByTitle(String title,String id);
    Map findCustBook(String title, String userid, Integer page);
    void deleteCollections(String bid,String uid);
}
