package com.ht.redisdistributlock.redislock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author mbql
 * @date 2020/3/31 18:49
 */
public class DistributedLock {

    private final JedisPool jedisPool;

    public DistributedLock(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * @param lockName       锁名称
     * @param acquireTimeout 获取超时时间
     * @param timeout        //锁超时时间
     * @return
     */
    public String lockWithTimeout(String lockName, long acquireTimeout, long timeout) {
        Jedis conn = null;
        String retIdentifire = null;
        try {
            //获取连接
            conn = jedisPool.getResource();
            //随机生成一个value
            String identifire = UUID.randomUUID().toString();
            //锁名，即key值
            String lockkey = "lock:" + lockName;
            //超时时间，上锁后超过此时间，自动释放锁
            int lockExpire = (int) (timeout / 1000);
            //放弃锁的超时时间，超过这个时间则放弃获取锁
            long end = System.currentTimeMillis() + acquireTimeout;
            while (System.currentTimeMillis() < end) {
                if (conn.setnx(lockkey, identifire) == 1) {
                    //设置锁过期时间
                    conn.expire(lockkey, lockExpire);
                    //返回value值，用于释放锁时间确认
                    retIdentifire = identifire;
                    return retIdentifire;
                }
                //返回-1代表key没有设置过期时间，为key设置过期时间
                if (conn.ttl(lockkey) == -1) {
                    conn.expire(lockkey, lockExpire);
                }
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (JedisException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return retIdentifire;
    }

    /**
     * 释放锁
     * @param lockName  锁名称
     * @param identifire  释放锁标识
     * @return
     */
    public boolean releaseLock(String lockName, String identifire){
        Jedis coon = null;
        String lockKey = "lock:" + lockName;
        boolean retFlag = false;
        try {
            //获取连接
            coon = jedisPool.getResource();
            while (true){
                //监视lock，开启事务
                coon.watch(lockKey);
                //通过前面返回的value值判断是不是该锁，若是该锁，则删除，释放锁
                if (identifire.equals(coon.get(lockKey))){
                    Transaction transaction = coon.multi();
                    transaction.del(lockKey);
                    List<Object> exec = transaction.exec();
                    if (exec == null){
                        continue;
                    }
                    retFlag = true;
                }
                coon.unwatch();
                break;
            }
        }catch (JedisException e){
            e.printStackTrace();
        }finally {
            if (coon != null){
                coon.close();
            }
        }
        return retFlag;
    }
}
