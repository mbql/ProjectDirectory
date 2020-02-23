package com.zm.shiro.service;

import com.zm.shiro.bean.User;

/**
 * @author mbql
 * 登录接口
 * @date 2020/2/23 16:30
 */
public interface LoginService {

    /**
     * 根据用户名查询用户
     * @param getMapByName
     * @return
     */
    User getUserByName(String getMapByName);

}
