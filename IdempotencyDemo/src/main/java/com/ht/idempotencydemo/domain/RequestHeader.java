package com.ht.idempotencydemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mbql
 * @date 2020/3/28 14:52
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestHeader {

    private String token;
    private String version;
    private String platform;

}
