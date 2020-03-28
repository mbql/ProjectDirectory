package com.ht.idempotencydemo.exception;

import lombok.Data;

/**
 * @author mbql
 * @date 2020/3/28 15:53
 */
@Data
public class RepeatException extends RuntimeException {
    private String code;
    public RepeatException() {
        super("请勿重复请求！！！");
        this.code = "10002";
    }
}
