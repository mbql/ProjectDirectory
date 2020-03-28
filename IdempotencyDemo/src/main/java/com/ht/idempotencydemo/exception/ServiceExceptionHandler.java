package com.ht.idempotencydemo.exception;

import com.ht.idempotencydemo.domain.CommenResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author mbql
 * @date 2020/3/28 16:16
 */
@RestControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

    @ExceptionHandler(TokenInvalidException.class)
    public CommenResult onException(TokenInvalidException ex){
        log.error(ex.getMessage());
        return new CommenResult("10001",ex.getMessage());
    }

    @ExceptionHandler(RepeatException.class)
    public CommenResult onException(RepeatException ex){
        log.error(ex.getMessage());
        return new CommenResult(ex.getCode(),ex.getMessage());
    }
}
