package com.ht.idempotencydemo.service.impl.impl;

import com.ht.idempotencydemo.service.TokenService;
import com.ht.idempotencydemo.utils.SnowflakeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author mbql
 * @date 2020/3/28 15:17
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getToken() {
        //获取全局唯一Id
        long id = SnowflakeUtil.nextId();
        //存入redis中
        stringRedisTemplate.opsForValue().set(String.valueOf(id), UUID.randomUUID().toString(),10, TimeUnit.MINUTES);
        return String.valueOf(id);
    }

    @Override
    public Boolean checkToken(String token) {
        return stringRedisTemplate.delete(token);
    }
}
