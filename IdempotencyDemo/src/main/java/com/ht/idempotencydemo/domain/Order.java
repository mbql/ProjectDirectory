package com.ht.idempotencydemo.domain;

import lombok.*;

import java.io.Serializable;

/**
 * @author mbql
 * @date 2020/3/28 14:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Order implements Serializable {
    private String orderId;
    private Long money;
    private String name;
    private String goodsId;
}
