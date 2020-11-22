package com.ht.seckill.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀库存表(SeckillStore)实体类
 *
 * @author sueno
 * @since 2020-07-11 14:17:52
 */
@Data
public class SeckillStore implements Serializable {
    private static final long serialVersionUID = 398926003326567028L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 商品id
    */
    private Integer proId;
    /**
    * 秒杀商品名称
    */
    private String sckillProName;
    /**
    * 秒杀商品价格
    */
    private Double sckillProPrice;
    /**
    * 秒杀商品库存
    */
    private Integer sckillProStore;
    /**
    * 秒杀开始时间
    */
    private Date sckillStartTime;
    /**
    * 秒杀结束时间
    */
    private Date sckillEndTime;
    /**
    * 秒杀状态（0.未开始 1.已开始 2.已结束 3.库存不足 4.秒杀成功）
    */
    private Object sckillStatus;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 是否删除 0.未删 1.已删
    */
    private Object isDelete;

}
