package com.ht.seckill.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀订单项表(SeckillOrderItem)实体类
 *
 * @author sueno
 * @since 2020-07-11 14:17:52
 */
@Data
public class SeckillOrderItem implements Serializable {
    private static final long serialVersionUID = 704833101812929295L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 产品id
    */
    private Integer proId;
    /**
    * 订单id
    */
    private Integer orderId;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 是否删除：0.未删 1.已删
    */
    private Object isDelete;

}
