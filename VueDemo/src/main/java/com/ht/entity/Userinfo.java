package com.ht.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户表(Userinfo)实体类
 *
 * @author makejava
 * @since 2020-03-11 16:39:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Userinfo implements Serializable {
    private static final long serialVersionUID = -20186288168654241L;
    /**
    * 用户Id
    */
    private Integer id;
    /**
    * 用户名
    */
    private String username;
    /**
    * 用户密码
    */
    private String password;
    /**
    * 创建时间
    */
    private Date createtime;
    /**
    * 更新时间
    */
    private Date updatetime;

}
