package com.literature.service.impl;

import com.literature.entity.Books;
import com.literature.entity.Comments;
import com.literature.entity.Nominate;
import com.literature.repository.BookRepository;
import com.literature.repository.CollectionsRepository;
import com.literature.repository.CommentRepository;
import com.literature.repository.NominateRepository;
import com.literature.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private NominateRepository nominateRepository;
    @Autowired
    private CollectionsRepository collectionsRepository;

    @Override
    public List<Books> find(String title, Integer page, Integer size) {
        return bookRepository.find(title,page,size);
    }



    @Override
    public void addBook(Books book) {
        bookRepository.save(book);
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
    public List<Books> findBooksByTitle(String title) {
        return bookRepository.findBooksByTitle(title);
    }

    @Override
    public List<Comments> findComment(String title, Integer page, Integer size) {
        return commentRepository.find(title,page,size);
    }

    @Override
    public List<Comments> findCommentByTitle(String title) {
        return commentRepository.findByTitle(title);
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

    @Override
    public void setNominate(Nominate nominate) {
        nominateRepository.save(nominate);
    }

    @Override
    public List<Comments> findCustComment(String title, String id, Integer page, Integer size) {
        return commentRepository.findCust(title,id,page,10);
    }

    @Override
    public List<Comments> findCustCommentByTitle(String title, String id) {
        return commentRepository.findCustByTitle(title,id);
    }

    @Override
    public Map findCustBook(String title, String userid, Integer page) {
        Map map = new HashMap();
        map.put("total",bookRepository.findCustTitle(title,userid).size());
        map.put("bookList",bookRepository.findCustByUserid(title,userid,page));
        return map;
    }

    @Override
    public void deleteCollections(String bid, String uid) {
         collectionsRepository.deleteCollection(bid,uid);
    }
}
