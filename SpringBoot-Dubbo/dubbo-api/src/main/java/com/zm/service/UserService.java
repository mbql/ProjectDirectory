package com.zm.service;

import com.zm.bean.Address;

import java.util.List;

/**
 * @author mbql
 * 用户接口
 * @date 2020/2/27 19:44
 */
public interface UserService {

    /**
     * 根据用户id查询地址信息
     * @param id
     * @return
     */
    List<Address> findAddressesByUserID(Integer id);

}
