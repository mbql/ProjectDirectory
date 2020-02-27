package com.zm.service;

import com.zm.bean.Address;

import java.util.List;

/**
 * @author mbql
 * 订单接口
 * @date 2020/2/27 19:42
 */
public interface OrderService {

    /**
     * 根据id查询所有地址信息
     * @param id
     * @return
     */
    List<Address> init(Integer id);

}
