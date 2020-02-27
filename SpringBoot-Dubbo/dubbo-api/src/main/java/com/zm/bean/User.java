package com.zm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author mbql
 * 用户类
 * @date 2020/2/27 19:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Integer id;

    private String username;

    private String password;

    private Address address;

}
