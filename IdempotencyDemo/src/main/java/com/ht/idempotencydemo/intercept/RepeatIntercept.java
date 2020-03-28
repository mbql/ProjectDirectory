package com.ht.idempotencydemo.intercept;

import com.ht.idempotencydemo.annotation.RepeatLimiter;
import com.ht.idempotencydemo.domain.RequestHeader;
import com.ht.idempotencydemo.exception.RepeatException;
import com.ht.idempotencydemo.service.TokenService;
import com.ht.idempotencydemo.utils.RequestContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author mbql
 * 防止重复提交拦截器
 * @date 2020/3/28 15:14
 */
@Component
public class RepeatIntercept implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod){
            //获取方法上参数
            RepeatLimiter repeatLimiter = AnnotationUtils.findAnnotation(((HandlerMethod) handler).getMethod(), RepeatLimiter.class);
            if (Objects.isNull(repeatLimiter)){
                //获取Controller类上注解
                repeatLimiter = AnnotationUtils.findAnnotation(((HandlerMethod) handler).getBean().getClass(),RepeatLimiter.class);
            }
            //使用注解需要拦截认证
            if (Objects.nonNull(repeatLimiter)){
                //获取全局Token，表单提交的唯一Id
                RequestHeader info = RequestContextUtils.getHeaderInfo();
                //没有携带Token抛异常，这里的异常需要全局捕获
                if (StringUtils.isEmpty(info.getToken()))
                    throw new  RepeatException();
                //校验Token
                Boolean flag = tokenService.checkToken(info.getToken());
                //删除失败处理
                if (Boolean.FALSE.equals(flag))
                    //抛出重复提交异常
                    throw new RepeatException();
            }
        }
        return true;
    }
}
