package com.zm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author mbql
 * 用户地址类
 * @date 2020/2/27 19:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    private Integer userId;
    private String street;

}
