package com.bnd.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author mbql
 * @date 2020/8/22 12:52
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        /**
         * 创建线程池的3种方式
         * 1、new ThreadPoolExecutor(...)
         * 2、new ScheduledThreadPoolExecutor(...)
         * 3、new ForkJoinPool()
         */
        // 第一种方式（一般也是最常用的一种方式 ThreadPoolExecutor()）
        /*ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1),
                new ThreadFactoryBuilder().build(),new ThreadPoolExecutor.AbortPolicy());
        try {
            for (int i = 1; i <= 5; i++) {
                executor.execute(new Runnable() {       // 任务一
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3 * 1000);
                            System.out.println("threadName：" + Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            executor.shutdown();    // 释放资源
        }*/

        /**
         * ThreadLocal本地线程，子线程的变量操作，并不影响主线程的值。
         * 通过这一点能够说明，主线程与子线程之间的相互操作，并不影响各自的实现
         */
        final ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1),
                new ThreadFactoryBuilder().build(),new ThreadPoolExecutor.DiscardOldestPolicy());
        threadLocal.set("currentID");       // 当前设置值
        try {
            Future<String> result = executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    threadLocal.set("currentID");       // 先执行set，然后再执行remove，是否还能获取到值？答案：否
                    threadLocal.remove();
                    System.out.println("threadName：" + Thread.currentThread().getName() + "--threadLocal：" + threadLocal.get());
                    return "返回执行结果...";
                }
            });
            // 获取返回结果
            System.out.println("threadName：" + Thread.currentThread().getName() + "--result：" + result.get(5L, TimeUnit.SECONDS) + "threadLocal：" + threadLocal.get());   // 设置为5秒
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            executor.shutdown();        // 记得用完释放资源
        }


        // 第二种方式（默认最大线程池大小Integer.MAX_VALUE，采用延迟队列缓冲，线程池空闲时间线程存活为0）
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5, new ThreadPoolExecutor.DiscardOldestPolicy());

        // 第三种方式
        ForkJoinPool forkJoinPool = new ForkJoinPool();


    }

}
