package com.literature.controller;

import com.alibaba.fastjson.JSON;
import com.literature.common.JsonApi;
import com.literature.entity.Books;
import com.literature.entity.Comments;
import com.literature.entity.CustomerInfo;
import com.literature.service.IBookService;
import com.literature.service.ICustomerInfoService;
import com.literature.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private IBookService bookService;
    @Autowired
    private ICustomerInfoService customerInfoService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Books> findAll() {
        return bookService.findAll();
    }

    @RequestMapping(value = "/title")
    @ResponseBody
    public List<Books> findByTitle(String title) {
        return bookService.findByName(title);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonApi deleteBook(String id) {
        JsonApi api = new JsonApi();
        bookService.deleteById(id);
        return api;
    }

    @RequestMapping(value = "/detail")
    @ResponseBody
    public Books findById(String id) {
        return bookService.findById(id);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonApi updateBook(@RequestBody Books params) {
        JsonApi api = new JsonApi();
        bookService.updateBook(params);
        return api;
    }

    @RequestMapping("/comment/list")
    @ResponseBody
    public List<Comments> findComentList() {
        return bookService.findCommentAll();
    }

    @RequestMapping("/comment/detail")
    @ResponseBody
    public CommentVo findComentById(String id) {
        Comments c1 = bookService.findComnentById(id);
        CustomerInfo c2 = customerInfoService.findById(c1.getUserId());
        CommentVo cv = new CommentVo();
        cv.setId(c1.getId());
        cv.setTitle(c1.getTitle());
        cv.setUsername(c2.getUsername());
        cv.setUserid(c2.getId());
        cv.setContent(c1.getContent());
        cv.setCreated(c1.getCreated());
        cv.setRating(c1.getRating());
        return cv;
    }

    @RequestMapping(value = "/comment/delete")
    @ResponseBody
    public JsonApi deleteComment(String id) {
        JsonApi api = new JsonApi();
        bookService.deleteCommentById(id);
        return api;
    }

}
