package com.ht.redisdistributlock.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author mbql
 * @date 2020/3/31 19:54
 */
public class DistributLockTest {

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        for (int i = 0; i < 50; i++) {
            ThreadA threadA = new ThreadA(service);
            new Thread(threadA).start();
            TimeUnit.MILLISECONDS.sleep(800);
            if (i > 48){
                System.out.println("已抢完...");
                new Thread(threadA).interrupt();
            }
        }
    }
}
