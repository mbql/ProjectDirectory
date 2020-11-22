package com.bnd.mapstruct.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description
 * @Author sueno
 * @Date 2020/11/22 12:38
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {

    private Long id;
    private String username;
    private String password;
    private Integer sex;
    private LocalDate birthday;
    private LocalDateTime createTime;
    private String config;
    private String test;

    @Override
    public String toString() { return JSONObject.toJSONString(this); }
}
