package com.ht.ssoserver.utils;

import org.springframework.data.redis.connection.RedisScriptingCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author mbql
 * 操作Redis常用的工具类
 * @date 2020/3/29 14:44
 */
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 默认redis使用jdkSerializationRedisSerializer,这样存入redis时进行序列化变为二进制这样不方便查看看
     * 需要自行设置为字符串序列化方便查看存入的值
     * 值得注意的是当我们进行模糊查询时，由于没有设置字符串序列化会影响我们模糊查询的结果
     * 当然如果要存入对象我们需要让实体类（pojo）实现Serializable接口
     */
    @PostConstruct
    public void Init() {
        RedisSerializer<String> stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.afterPropertiesSet();
    }

    /**
     * 常用操作对象
     * 1.字符串操作
     * 2.散列集合操作（hash）
     */
    public void stringOps() {
        //1.字符串操作
        redisTemplate.opsForValue().set("mbql", "mbql");
        System.out.println(redisTemplate.opsForValue().get("mbql"));
    }

    public void hashOps() {
        //2.hash操作
        redisTemplate.opsForHash().put("mbqlHash", "myself", "mbql");
        redisTemplate.opsForHash().get("mbqlHash", "name");
        Map<Object, Object> mbqlHash = redisTemplate.opsForHash().entries("mbqlHash");
        mbqlHash.entrySet().iterator().forEachRemaining(a -> System.out.println(a.getKey() + ":" + a.getValue()));
        //设置过期
        redisTemplate.expire("mbqlHash", 10, TimeUnit.SECONDS);
    }

    /**
     * 当然我们怎么操作lua脚本呢？，lua脚本能保证我们操作的原子性
     * 值得注意的是springboot默认使用的是lettuce操作Redis应为它的异步和集群支持
     */
    public void opScript() {
        //获取底层连接对象类似（Jedis）
        RedisScriptingCommands redisScriptingCommands = redisTemplate.getConnectionFactory().getConnection().scriptingCommands();
        //返回一个乱脚本标识码
        String sha1 = redisScriptingCommands.scriptLoad("return redis.call('SET',KEYS[1],ARGV[1])".getBytes());
        System.out.println("lua脚本标识码" + sha1);
        //执行lua脚本
        Object o = redisScriptingCommands.evalSha(sha1, ReturnType.STATUS, 1, "MBQLLUA".getBytes(), "MBQLLUA".getBytes());
        System.out.println("执行结果！" + o.toString());

    }

    /**
     * 模糊匹配，？（单个字符），*（多个字符）
     */
    public void likeOps() {
        Iterator<Object> iterator = redisTemplate.keys("mbql*").iterator();
        StringBuilder stringBuilder = new StringBuilder(".");
        iterator.forEachRemaining(a -> stringBuilder.append(a));
        System.out.println(stringBuilder.toString());
    }
}
