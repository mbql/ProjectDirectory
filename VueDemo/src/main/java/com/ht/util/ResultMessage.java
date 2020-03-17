package com.ht.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author mbql
 * @date 2020/3/14 15:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultMessage implements Serializable {
    private int code;
    private String message;
}
