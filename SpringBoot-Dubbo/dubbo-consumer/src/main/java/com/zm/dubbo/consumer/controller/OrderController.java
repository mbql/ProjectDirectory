package com.zm.dubbo.consumer.controller;

import com.zm.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author mbql
 * @date 2020/2/27 20:08
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("order")
    public Object init(Integer id) {
        return orderService.init(id);
    }

}
