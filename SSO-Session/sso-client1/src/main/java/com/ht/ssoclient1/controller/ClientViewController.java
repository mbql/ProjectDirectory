package com.ht.ssoclient1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author mbql
 * @date 2020/3/29 16:24
 */
@Controller
public class ClientViewController {

    @RequestMapping("hello")
    public String hello(HttpServletRequest request, HttpSession session){
        System.out.println("名字"+request.getSession().getAttribute("name"));
        System.out.println("client-hello-sessionid:"+session.getId());
        return "my";
    }
}
