package com.ht.idempotencydemo.service.impl;

import com.ht.idempotencydemo.domain.Order;
import com.ht.idempotencydemo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author mbql
 * @date 2020/3/28 16:07
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Override
    public void save(Order order) {
        log.debug(order.toString());
        log.debug("下单成功.........");
    }
}
