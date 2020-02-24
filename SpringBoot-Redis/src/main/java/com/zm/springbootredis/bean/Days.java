package com.zm.springbootredis.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mbql
 * @date 2020/2/24 16:48
 */
@Data
public class Days implements Serializable {
    private Integer openId;
    private Integer daysId;
    //每天的标题
    private String title;
    //代办事项的数量
    private Integer itemNumber;
    //日程
    private Date date;
}
