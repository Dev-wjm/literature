package com.literature.controller;

import com.alibaba.fastjson.JSON;
import com.literature.common.JsonApi;
import com.literature.entity.Books;
import com.literature.entity.Comments;
import com.literature.entity.CustomerInfo;
import com.literature.entity.Nominate;
import com.literature.service.IBookService;
import com.literature.service.ICustomerInfoService;
import com.literature.util.IDUtil;
import com.literature.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/book")
public class BookController {

    @Autowired
    private IBookService bookService;
    @Autowired
    private ICustomerInfoService customerInfoService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public JsonApi findAll(String title,Integer page) {
        JsonApi api = new JsonApi();
        Integer total = bookService.findBooksByTitle(title).size();
        List<Books> bookList = new ArrayList<>();
        if (null == page || page == 1) {
            bookList = bookService.find(title,0,10);
        }else {
            bookList = bookService.find(title,(page-1)*10,10);
        }
        Map map = new HashMap();
        map.put("total",total);
        map.put("bookList",bookList);
        api.setData(map);
        return api;
    }

    @RequestMapping(value = "/list2")
    @ResponseBody
    public JsonApi findCustAll(String title,String id,Integer page) {
        JsonApi api = new JsonApi();
        Map map = new HashMap();
        if (null == page || page == 1) {
            map = bookService.findCustBook(title,id,0);
        }else {
            map = bookService.findCustBook(title,id,(page-1)*10);
        }
        api.setData(map);
        return api;
    }

    // 删除收藏
    @RequestMapping(value = "/collection/delete")
    @ResponseBody
    public JsonApi deleteCollection(String bid,String uid) {
        JsonApi api = new JsonApi();
        bookService.deleteCollections(bid,uid);
        return api;
    }

    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonApi addBook(@RequestBody Books params){
        JsonApi api = new JsonApi();
        String id = IDUtil.getId();
        params.setId(id);
        params.setCollection(0);
        params.setUrl("/api/book/get");
        bookService.addBook(params);
        return api;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonApi updateBook(@RequestBody Books params) {
        JsonApi api = new JsonApi();
        bookService.updateBook(params);
        return api;
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public Books findById(String id) {
        return bookService.findById(id);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public JsonApi deleteBook(String id) {
        JsonApi api = new JsonApi();
        bookService.deleteById(id);
        return api;
    }





    @RequestMapping("/comment/list")
    @ResponseBody
    public JsonApi findComentList(String title,Integer page) {
        JsonApi api = new JsonApi();
        Map map = new HashMap();
        Integer total = bookService.findCommentByTitle(title).size();
        List<Comments> commentList = new ArrayList<>();
        if (null==page || page == 1) {
            commentList = bookService.findComment(title,1,10);
        }else {
            commentList = bookService.findComment(title,(page-1)*10,10);
        }
        map.put("total",total);
        map.put("commentList",commentList);
        api.setData(map);
        return api;
    }

    @RequestMapping("/comment/list2")
    @ResponseBody
    public JsonApi findCustComentList(String title,String id,Integer page) {
        JsonApi api = new JsonApi();
        Map map = new HashMap();
        Integer total = bookService.findCustCommentByTitle(title,id).size();
        List<Comments> commentList = new ArrayList<>();
        if (null==page || page == 1) {
            commentList = bookService.findCustComment(title,id,0,10);
        }else {
            commentList = bookService.findCustComment(title,id,(page-1)*10,10);
        }
        map.put("total",total);
        map.put("commentList",commentList);
        api.setData(map);
        return api;
    }

    @RequestMapping("/comment/get")
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

    @RequestMapping(value = "/nominate/update")
    @ResponseBody
    public JsonApi nominateSet(@RequestBody Map params) {
        JsonApi api = new JsonApi();
        Nominate nominate = new Nominate();
        nominate.setId("1");
        nominate.setCondition(params.get("nominate").toString());
        bookService.setNominate(nominate);
        return api;
    }

}
