package com.zm.shiro.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author mbql
 * 自定义异常处理类
 * @date 2020/2/23 17:53
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public static String ErrorHandler(AuthorizationException e) {
        log.error("没有通过权限验证" + e);
        return "没有通过权限验证...";
    }

}
