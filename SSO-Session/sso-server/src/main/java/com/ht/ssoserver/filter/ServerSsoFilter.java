package com.ht.ssoserver.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mbql
 * 自定义Filter过滤器
 * @date 2020/3/29 14:57
 */
public class ServerSsoFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        Cookie[] cookies = httpServletRequest.getCookies();
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
        String ssoToken = (String) httpServletRequest.getSession().getAttribute("ssoToken");
        String returnUrl = httpServletRequest.getParameter("returnUrl");
        //Session和Cookie中的值都应该跳转到目标页面
        if (returnUrl != null && !"".equals(returnUrl) && ssoToken != null && ssoToken.equals(token) && !"".equals(ssoToken)){
            httpServletResponse.sendRedirect(returnUrl);
        }
        //登录操作不拦截
        if (httpServletRequest.getServletPath().contains("login")){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //校验Token
        if (ssoToken != null && ssoToken.equals(token) && !"".equals(ssoToken)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //校验Token不通过，跳转至SSO认证中心
        httpServletResponse.sendRedirect("http://localhost:8085/loginParent?returnUrl=http://localhost:8085/loginParent");
    }
}
