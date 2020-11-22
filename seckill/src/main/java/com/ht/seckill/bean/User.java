package com.ht.seckill.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表(User)实体类
 *
 * @author sueno
 * @since 2020-07-11 14:17:52
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -74475953890741466L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 昵称
    */
    private String userName;
    /**
    * 出生日期
    */
    private Date birthday;
    /**
    * 性别：0.男 1.女
    */
    private Object gender;
    /**
    * 用户地址
    */
    private String address;
    /**
    * 手机号码
    */
    private String phone;
    /**
    * 状态：0.禁用 1.正常
    */
    private Object status;
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
