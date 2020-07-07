package com.ht.mvc.service.impl;

import com.ht.mvc.annotation.Service;
import com.ht.mvc.service.UserService;

/**
 * @author mbql
 * @date 2020/7/5 12:56
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String queryUser() {
        return "您好，sueno，欢迎来到java殿堂。。。。。";
    }
}
