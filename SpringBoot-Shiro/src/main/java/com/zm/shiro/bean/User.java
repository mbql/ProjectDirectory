package com.zm.shiro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

/**
 * @author mbql
 * 用户实体类
 * @date 2020/2/23 16:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    /**
     * 用户编号
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
     * 角色集合
     */
    private Set<Role> roles;

}
