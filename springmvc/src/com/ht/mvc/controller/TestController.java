package com.ht.mvc.controller;

import com.ht.mvc.annotation.Autowired;
import com.ht.mvc.annotation.Controller;
import com.ht.mvc.annotation.RequestMapping;
import com.ht.mvc.service.UserService;

/**
 * @author mbql
 * @date 2020/7/5 12:54
 */
@Controller
@RequestMapping("user")
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("query/info")
    public String queryUser(){
        return  userService.queryUser();
    }

    @RequestMapping("query/index")
    public String queryIndex(){
        return  "index.jsp";
    }

}
