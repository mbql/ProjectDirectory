package com.ht.service;

import com.ht.entity.Userinfo;
import com.ht.util.ResultToken;

import java.util.List;

/**
 * 用户表(Userinfo)表服务接口
 *
 * @author makejava
 * @since 2020-03-11 16:39:49
 */
public interface UserinfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Userinfo queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Userinfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userinfo 实例对象
     * @return 实例对象
     */
    Userinfo insert(Userinfo userinfo);

    /**
     * 修改数据
     *
     * @param userinfo 实例对象
     * @return 实例对象
     */
    Userinfo update(Userinfo userinfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 登录接口
     * @param userinfo
     * @return
     */
    ResultToken login(Userinfo userinfo);

}
