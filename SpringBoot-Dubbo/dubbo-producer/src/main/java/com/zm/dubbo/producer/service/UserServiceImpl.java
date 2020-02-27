package com.zm.dubbo.producer.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.zm.bean.Address;
import com.zm.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mbql
 * @date 2020/2/27 19:52
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<Address> findAddressesByUserID(Integer id) {
        Address address = new Address(1,"深圳市南山区");
        Address address2 = new Address(1,"深圳市宝安区");
        List<Address> listsAddresses = new ArrayList<Address>();
        listsAddresses.add(address);
        listsAddresses.add(address2);
        return listsAddresses;
    }
}
