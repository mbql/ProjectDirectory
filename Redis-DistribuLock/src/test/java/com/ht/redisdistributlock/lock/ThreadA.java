package com.ht.redisdistributlock.lock;

/**
 * @author mbql
 * @date 2020/3/31 20:22
 */
public class ThreadA implements Runnable {

    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}
