package com.zm.springbootredis;

import com.zm.springbootredis.bean.Days;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootRedisApplicationTests {

    @Autowired
    private RedisTemplate<String, Days> redisTemplate;

    @Test
    void contextLoads() {
        // redisTemplate.opsForValue().set("mykey","myvalue");
        // redisTemplate.opsForValue().set("a","mbql");
        // System.out.println(redisTemplate.opsForValue().get("a"));
        // redisTemplate.opsForValue().set("v","hello world!");
        // System.out.println(redisTemplate.opsForValue().get("v"));
        //封装一个对象
       /* Days days = new Days();
        days.setOpenId(8);
        days.setDaysId(70);
        days.setTitle("SpringBoot源码学习课堂");
        days.setItemNumber(8);
        days.setDate(new Date());
        //将该对象添加到redis中
        redisTemplate.opsForValue().set("springboot",days);
        //从redis获取刚添加的对象
        System.out.println(redisTemplate.opsForValue().get("springboot"));*/
        //封装要删除的key
        List<String> list = new ArrayList<>();
        list.add("days");
        list.add("dou");
        list.add("dp");
        list.add("java");
        list.add("spring");
        list.add("springboot");
        list.add("mykey");
        list.add("v");
        list.add("xin");
        //从redis删除不需要的数据
        redisTemplate.delete(list);
    }
}
