package com.zm.dubbo.consumer.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zm.bean.Address;
import com.zm.service.OrderService;
import com.zm.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mbql
 * @date 2020/2/27 20:02
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Reference
    private UserService userService;

    @Override
    public List<Address> init(Integer id) {
        List<Address> addresses = userService.findAddressesByUserID(id);
        System.out.println("查看到该用户的地址");
        return addresses;
    }
}
