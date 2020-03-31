package com.ht.redisdistributlock.redislock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mbql
 * @date 2020/3/31 15:27
 */
public class RedisLockImpl implements Lock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 锁的资源
     */
    private String resourceName;

    /**
     * 锁的等待时间
     */
    int timeout;

    public RedisLockImpl(StringRedisTemplate stringRedisTemplate, String resourceName, int timeout) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.resourceName = "lock_" + resourceName;
        this.timeout = timeout;
    }

    private Lock lock = new ReentrantLock();

    /**
     * 一直要等到抢到锁为止
     */
    @Override
    public void lock() {
        //限制JVM之间锁的竞争，分布式锁
        lock.lock();
        try {
            while (!tryLock()) {
                //订阅指定的Redis主题，接收释放锁的资源
                stringRedisTemplate.execute(new RedisCallback<Long>() {
                    @Override
                    public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        try {
                            // subscribe立马返回，是否订阅完毕，异步触发
                            CountDownLatch countDownLatch = new CountDownLatch(1);
                            redisConnection.subscribe((message, pattern) -> {
                                // 收到消息，不管结果，立刻再次抢锁
                                countDownLatch.countDown();
                            }, ("release_lock_" + resourceName).getBytes());
                            //等待有通知，才继续循环
                            countDownLatch.await(timeout, TimeUnit.SECONDS);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        return 0L;
                    }
                });
            }
        } finally {
            //释放资源
            lock.unlock();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        stringRedisTemplate.delete(resourceName);
        //通过Redis发布订阅机制，发送一个通知给其他等待的请求
        stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.publish(("release_lock_" + resourceName).getBytes(), resourceName.getBytes());
            }
        });
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
