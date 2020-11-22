package com.bnd.mapstruct.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @Description
 * @Author sueno
 * @Date 2020/11/22 12:54
 */
@Data
@Accessors(chain = true)
public class UserVo implements Serializable {

    private Long id;
    private String username;
    private String password;
    private Integer gender;
    private LocalDate birthday;
    private String createTime;
    private List<UserConfig> config;
    private String test;

    @Data
    public static class UserConfig {
        private String field1;
        private Integer field2;
    }

    @Override
    public String toString() { return JSONObject.toJSONString(this); }

}
