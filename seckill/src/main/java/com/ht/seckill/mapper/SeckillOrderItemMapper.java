package com.ht.seckill.mapper;

import com.ht.seckill.bean.SeckillOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 秒杀订单项表(SeckillOrderItem)表数据库访问层
 *
 * @author sueno
 * @since 2020-07-11 14:24:42
 */
@Mapper
public interface SeckillOrderItemMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SeckillOrderItem queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SeckillOrderItem> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param seckillOrderItem 实例对象
     * @return 对象列表
     */
    List<SeckillOrderItem> queryAll(SeckillOrderItem seckillOrderItem);

    /**
     * 新增数据
     *
     * @param seckillOrderItem 实例对象
     * @return 影响行数
     */
    int insert(SeckillOrderItem seckillOrderItem);

    /**
     * 修改数据
     *
     * @param seckillOrderItem 实例对象
     * @return 影响行数
     */
    int update(SeckillOrderItem seckillOrderItem);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
