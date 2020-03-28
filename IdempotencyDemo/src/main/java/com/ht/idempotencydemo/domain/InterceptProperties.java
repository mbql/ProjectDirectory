package com.ht.idempotencydemo.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mbql
 * @date 2020/3/28 14:47
 */
@Data
@ConfigurationProperties(prefix = "spring.intercept")
public class InterceptProperties {

    /**
     * 请求头拦截器的排除uri
     */
    private String[] headInterceptExclude=new String[]{};

    /**
     * 重复提交拦截器排除的uri
     */
    private String[] repeatInterceptExclude=new String[]{};

}
