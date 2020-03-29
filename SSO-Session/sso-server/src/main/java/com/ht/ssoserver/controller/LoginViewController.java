package com.ht.ssoserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mbql
 * @date 2020/3/29 15:32
 */
@Controller
public class LoginViewController {

    /**
     * 跳转到登录页面
     * @param servletRequest
     * @return
     */
    @RequestMapping("loginParent")
    public String toLogin(HttpServletRequest servletRequest){
        System.out.println("loginServer-sessionid:"+servletRequest.getHeader("Cookie"));
        return "loginParent";
    }

    /**
     * 执行登录请求成功，设置Token给客户端
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    @RequestMapping("loginServer")
    public String login(HttpServletRequest servletRequest, HttpServletResponse servletResponse){
        Cookie[] cookies = servletRequest.getCookies();
        String token = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case "SESSION":
                        token = cookie.getValue();
                        break;
                    default:
                        break;
                }
            }
        }
        String id = token;
        String returnUrl = servletRequest.getParameter("returnUrl");
        System.out.println("returnUrl:"+returnUrl);
        System.out.println("server-hello-cookie:"+ servletRequest.getHeader("Cookie"));
        servletResponse.addCookie(new Cookie("ssoToken",id));
        servletRequest.getSession().setAttribute("ssoToken",id);
        return "redirect:"+returnUrl+"?ssoToken="+id;
    }
}
