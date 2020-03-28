package com.ht.idempotencydemo.utils;

/**
 * @author mbql
 * @date 2020/3/28 14:38
 */
public class SnowflakeUtil {

    // 系统开始时间截 (UTC 2018-01-01 00:00:00)
    private static final long startTime = 1514736000000L;
    // 机器id所占的位数
    private static final long workerIdBits = 5L;
    // 数据标识id所占的位数
    private static final long dataCenterIdBits = 5L;
    // 支持的最大机器id(十进制)，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
    // -1L 左移 5位 (worker id 所占位数) 即 5位二进制所能获得的最大十进制数 - 31
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    // 支持的最大数据标识id - 31
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    // 序列在id中占的位数
    private static final long sequenceBits = 12L;
    // 机器ID 左移位数 - 12 (即末 sequence 所占用的位数)
    private static final long workerIdMoveBits = sequenceBits;
    // 数据标识id 左移位数 - 17(12+5)
    private static final long dataCenterIdMoveBits = sequenceBits + workerIdBits;
    // 时间截向 左移位数 - 22(5+5+12)
    private static final long timestampMoveBits = sequenceBits + workerIdBits + dataCenterIdBits;
    // 生成序列的掩码(12位所对应的最大整数值)，这里为4095 (0b111111111111=0xfff=4095)
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~31)
     */
    private static long workerId = 0L;
    /**
     * 数据中心ID(0~31)
     */
    private static long dataCenterId = 0L;
    /**
     * 毫秒内序列(0~4095)
     */
    private static long sequence = 0L;
    /**
     * 上次生成ID的时间截
     */
    private static long lastTimestamp = -1L;

    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param dataCenterId 数据中心ID (0~31)
     */
    public SnowflakeUtil(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("Worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("DataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 线程安全的获得下一个 ID 的方法
     * @return
     */
    public static synchronized long nextId() {
        long timestamp = currentTime();
        //如果当前时间小于上一次ID生成的时间戳: 说明系统时钟回退过 - 这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出 即 序列 > 4095
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = blockTillNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }
        //上次生成ID的时间截
        lastTimestamp = timestamp;
        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - startTime) << timestampMoveBits)
                | (dataCenterId << dataCenterIdMoveBits)
                | (workerId << workerIdMoveBits)
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒 即 直到获得新的时间戳
     * @param lastTimestamp
     * @return
     */
    private static long blockTillNextMillis(long lastTimestamp) {
        long timestamp = currentTime();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTime();
        }
        return timestamp;
    }

    /**
     * 获得以毫秒为单位的当前时间
     * @return
     */
    private static long currentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            Long id = nextId();
            //二进制
            // System.out.println(Long.toBinaryString(id));
            System.out.println(id);
        }
    }
}
