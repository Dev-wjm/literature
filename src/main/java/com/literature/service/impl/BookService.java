package com.literature.service.impl;

import com.literature.entity.Books;
import com.literature.entity.Comments;
import com.literature.repository.BookRepository;
import com.literature.repository.CommentRepository;
import com.literature.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Books> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Books> findByName(String title) {
        return bookRepository.findBooksByTitle(title);
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Books findById(String id) {
        return bookRepository.findBooksById(id);
    }

    @Override
    public void updateBook(Books books) {
        bookRepository.save(books);
    }

    @Override
    public List<Comments> findCommentAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comments findComnentById(String id) {
        return commentRepository.findCommentsById(id);
    }

    @Override
    public void deleteCommentById(String id) {
        commentRepository.deleteById(id);
    }
}
