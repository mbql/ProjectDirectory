package com.zm.springbootrabbitmq.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author mbql
 * @date 2020/2/25 16:46
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String name;
    private String password;
}
