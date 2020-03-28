package com.ht.idempotencydemo.utils;

import com.ht.idempotencydemo.domain.RequestHeader;
import com.ht.idempotencydemo.domain.constants.HeaderConstant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import java.util.Objects;

/**
 * @author mbql
 * @date 2020/3/28 14:37
 */
public class RequestContextUtils {

    private RequestContextUtils(){}

    public static ServletRequest getRequest(){
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static RequestHeader getHeaderInfo(){
        return Objects.requireNonNull((RequestHeader) getRequest().getAttribute(HeaderConstant.HEADER_INFO));
    }

}
