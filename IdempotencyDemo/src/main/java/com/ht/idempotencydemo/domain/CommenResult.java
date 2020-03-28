package com.ht.idempotencydemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mbql
 * @date 2020/3/28 14:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommenResult<T> {

    private String code;
    private String message;
    private T body;

    public CommenResult(String code,String message){
        this(code,message,null);
    }
}
