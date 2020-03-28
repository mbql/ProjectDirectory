package com.ht.idempotencydemo.controller;

import com.ht.idempotencydemo.annotation.RepeatLimiter;
import com.ht.idempotencydemo.domain.CommenResult;
import com.ht.idempotencydemo.domain.Order;
import com.ht.idempotencydemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mbql
 * @date 2020/3/28 16:05
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 模拟下单
     * @param order
     * @return
     */
    @PostMapping("mock")
    @RepeatLimiter //保证幂等性
    public CommenResult add(@RequestBody Order order){
        orderService.save(order);
        return new CommenResult("200","下单成功...");
    }
}
