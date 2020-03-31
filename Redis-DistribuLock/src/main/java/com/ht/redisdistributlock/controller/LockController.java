package com.ht.redisdistributlock.controller;

import com.ht.redisdistributlock.redislock.RedisLockImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author mbql
 * @date 2020/3/31 15:59
 */
@RestController
@RequestMapping("api")
public class LockController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private RedisLockImpl redisLock;

    @GetMapping("add/{resourceName}/{timeout}")
    public String lockResource(@PathVariable String resourceName, @PathVariable int timeout){
        new Thread(new Runnable(){
            @Override
            public void run() {
                //第一次设置
                stringRedisTemplate.opsForValue().set("A","mbql");
                redisLock = new RedisLockImpl(stringRedisTemplate, resourceName, timeout);
                long start = System.currentTimeMillis();
                System.out.println("-------------------"+start+"----------------------");
                redisLock.lock();
                //第二次设置
                try {
                    TimeUnit.SECONDS.sleep(10);
                    stringRedisTemplate.opsForValue().set("B","lhl");
                    redisLock = new RedisLockImpl(stringRedisTemplate, resourceName, timeout);
                    long end = System.currentTimeMillis() - start;
                    System.out.println("-----------------"+end+"----------------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return "设置成功";
    }
}
