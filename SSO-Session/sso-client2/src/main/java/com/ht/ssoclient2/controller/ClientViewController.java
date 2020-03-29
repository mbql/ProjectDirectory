package com.ht.ssoclient2.controller;

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

    /**
     * 主页请求
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("hello")
    public String hello(HttpServletRequest request, HttpSession session){
        System.out.println("名字"+request.getSession().getAttribute("name"));
        System.out.println("client-hello-sessionid:"+session.getId());
        return "my";
    }

    /**
     * 退出登录
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request,HttpSession session){
        session.setMaxInactiveInterval(0);
        System.out.println("退出登录移除Session...");
        return "redirect:http://localhost:8085/loginParent?returnUrl=http://localhost:8087/hello";
    }
}
