package com.ht.idempotencydemo.service;

/**
 * @author mbql
 * @date 2020/3/28 15:16
 */
public interface TokenService {

    /**
     * 生成Token
     * @return
     */
    String getToken();

    /**
     * 校验token
     * @param token
     * @return
     */
    Boolean checkToken(String token);

}
