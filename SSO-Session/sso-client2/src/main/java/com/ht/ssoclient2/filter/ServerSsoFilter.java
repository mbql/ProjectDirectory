package com.ht.ssoclient2.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mbql
 * @date 2020/3/29 16:10
 */
public class ServerSsoFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String ssoToken = request.getSession().getAttribute("ssoToken").toString();
        System.out.println(ssoToken);
        if (request.getServletPath().contains("login")){
            filterChain.doFilter(request,response);
            return;
        }
        Cookie[] cookies = request.getCookies();
        String token = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                switch (cookie.getName()) {
                    case "ssoToken":
                        token = cookie.getValue();
                        break;
                    default:
                        break;
                }
            }
        }
        if (ssoToken !=null && ssoToken.equals(token) && !"".equals(ssoToken)){
            filterChain.doFilter(request,response);
            request.getSession().setAttribute("name","mbql");
            return;
        }
        //跳转至SSO认证中心
        response.sendRedirect("http://localhost:8085/loginParent?returnUrl=http://localhost:8087/hello");
    }
}
