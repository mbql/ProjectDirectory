package com.ht.idempotencydemo.exception;

/**
 * @author mbql
 * Token认证失效
 * @date 2020/3/28 15:06
 */
public class TokenInvalidException extends RuntimeException {

    public TokenInvalidException(String msg){
        super(msg);
    }
}
