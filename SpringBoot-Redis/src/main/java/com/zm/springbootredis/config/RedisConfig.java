package com.zm.springbootredis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.zm.springbootredis.bean.Days;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author mbql
 * Redis配置类
 * @date 2020/2/24 16:40
 */
@Configuration
public class RedisConfig {

    /*@Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new  StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }*/


    @Bean
    public RedisTemplate<String, Days> redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, Days> redisTemplate = new RedisTemplate<>();
        //配置关联连接工厂
        redisTemplate.setConnectionFactory(connectionFactory);
        //配置key的序列化方式
        redisTemplate.setKeySerializer(new  StringRedisSerializer());
        //配置values的序列化方式
        redisTemplate.setValueSerializer(new FastJsonRedisSerializer<>(Days.class));
        return redisTemplate;
    }
}
