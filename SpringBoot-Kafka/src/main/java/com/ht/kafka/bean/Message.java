package com.ht.kafka.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author mbql
 * @date 2020/3/1 20:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {

    private Long id;
    private String msg;
    private Date sendTime;

}
