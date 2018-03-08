package com.literature.service.impl;

import com.literature.common.JsonApi;
import com.literature.entity.*;
import com.literature.repository.*;
import com.literature.service.IBookService;
import com.literature.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private CustomerInfoRepository customerInfoRepository;

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

    @Override
    public void addCollection(Collections collections) {
        collectionsRepository.save(collections);
    }

    @Override
    public List<Books> getBookListByNominate(Integer page) {
        Nominate nominate = nominateRepository.findAll().get(0);
        List<Books> bookList = new ArrayList<>();
        if (null!=nominate && nominate.getCondition().equals("rating")) {
            bookList = bookRepository.getListByRating(page);
        }else if (null!=nominate && nominate.getCondition().equals("collection")) {
            bookList = bookRepository.getListByCollection(page);
        }else {
            // 如果没有设置推荐条件默认评分推荐
            bookList = bookRepository.getListByRating(page);
        }
        return bookList;
    }

    @Override
    public void addComment(Comments comments) {
        commentRepository.save(comments);
    }

    @Override
    public JsonApi getBooks(String id) {
        Map map = new HashMap();
        Books books = bookRepository.findBooksById(id);
        String score = bookRepository.getBookRating(id);
        map.put("book",books);
        map.put("score",score);
        return new JsonApi(map);
    }

    @Override
    public List<CommentVo> getComments(String id,Integer page) {
        List<Comments> list = new ArrayList<>();
        if (null!=page && page==1) {
            list = commentRepository.findCommentsByBookId(id,0);
        }else {
            list = commentRepository.findCommentsByBookId(id,(page-1)*10);
        }
        List<CommentVo> commentList = new ArrayList<>();
        for (Comments c1 :list) {
            CustomerInfo c = customerInfoRepository.findCustomerInfoById(c1.getUserId());
            CommentVo cv = new CommentVo();
            cv.setId(c1.getId());
            cv.setTitle(c1.getTitle());
            if (null!=c){
                cv.setUsername(c.getUsername());
            }
            cv.setUserid(c1.getUserId());
            cv.setContent(c1.getContent());
            cv.setCreated(c1.getCreated());
            cv.setRating(c1.getRating());
            commentList.add(cv);
        }
        return commentList;
    }

    @Override
    public List<String> getUsersId(String id) {
        return collectionsRepository.getUsersId(id);
    }
}
