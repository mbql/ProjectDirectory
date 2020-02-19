package com.zm.mybatis.entity.mapper;

import com.zm.mybatis.entity.User;

import java.util.List;

/**
 * @author mbql
 * @date 2020/2/19 16:03
 */
public interface UserMapper {

    /**
     * 根据id查询用户
     * @param id
     * @return user
     */
    User selectOne(Integer id);

    /**
     * 查询所有用户信息
      * @return
     */
    List<User> list();

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    int delUser(Integer id);



}
