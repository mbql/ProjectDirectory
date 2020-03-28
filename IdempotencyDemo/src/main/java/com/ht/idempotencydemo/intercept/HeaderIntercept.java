package com.ht.idempotencydemo.intercept;

import com.ht.idempotencydemo.domain.RequestHeader;
import com.ht.idempotencydemo.domain.constants.HeaderConstant;
import com.ht.idempotencydemo.exception.TokenInvalidException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mbql
 * 获取请求头的信息，具体校验逻辑读者自己实现
 * @date 2020/3/28 15:00
 */
@Component
public class HeaderIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取token
        String token = request.getHeader(HeaderConstant.TOKEN);
        //校验逻辑
        if (!validToken(token))
            throw new TokenInvalidException("TOKEN失效...");
        //获取其他参数
        RequestHeader header = RequestHeader.builder().token(token).build();
        //放入request中
        request.setAttribute(HeaderConstant.HEADER_INFO, header);
        return true;
    }

    /**
     * 校验Token，逻辑自己实现
     * @param token
     * @return
     */
    private boolean validToken(String token){
        return Boolean.TRUE;
    }
}
