package com.literature.service;

import com.literature.entity.Books;
import com.literature.entity.Comments;

import java.util.List;

public interface IBookService {

    List<Books> findAll();
    List<Books> findByName(String title);
    void deleteById(String id);
    Books findById(String id);
    void updateBook(Books books);

    List<Comments> findCommentAll();
    Comments findComnentById(String id);
    void deleteCommentById(String id);
}
