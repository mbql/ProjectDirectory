package com.ht.seckill.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品表(Product)实体类
 *
 * @author sueno
 * @since 2020-07-11 14:17:52
 */
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 282895098015942408L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 产品名称
    */
    private String proName;
    /**
    * 产品价格
    */
    private Double proPrice;
    /**
    * 产品库存
    */
    private Integer proStore;
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
