package com.ht.seckill.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单表(Order)实体类
 *
 * @author sueno
 * @since 2020-07-11 14:17:51
 */
@Data
public class Order implements Serializable {
    private static final long serialVersionUID = -43638533130925885L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 支付方式：0.微信支付 1.支付宝 2.银行卡
    */
    private Object payType;
    /**
    * 支付状态：0.待支付 1.已支付 2.已发货 3.已取消  4.评价
    */
    private Object payStatus;
    /**
    * 订单金额
    */
    private Double orderPrice;
    /**
    * 订单编号
    */
    private String orderNo;
    /**
    * 支付价格
    */
    private Double payPrice;
    /**
    * 支付时间
    */
    private Date payTime;
    /**
    * 订单留言
    */
    private String leavMessage;
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
