package com.ht.redisdistributlock.lock;

import com.ht.redisdistributlock.redislock.DistributedLock;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author mbql
 * @date 2020/3/31 20:20
 */
public class Service {

    private static JedisPool pool = null;
    private DistributedLock lock = new DistributedLock(pool);
    private int count = 500;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(200);
        // 设置最大空闲数
        config.setMaxIdle(8);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "134.175.206.167", 6379, 3000);
    }

    /**
     * 模拟秒杀
     */
    public void  seckill(){
        //返回锁的value值，供释放锁时进行判断
        String identifier = lock.lockWithTimeout("resource", 5000, 1000);
        System.out.println(Thread.currentThread().getName() + "获得了锁");
        System.out.println(--count);
        lock.releaseLock("resource",identifier);
    }
}
