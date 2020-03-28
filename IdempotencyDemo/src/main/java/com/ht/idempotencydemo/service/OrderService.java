package com.ht.idempotencydemo.service;

import com.ht.idempotencydemo.domain.Order;

/**
 * @author mbql
 * @date 2020/3/28 16:06
 */
public interface OrderService {

    /**
     * 模拟下单
     * @param order
     */
    void save(Order order);

}
