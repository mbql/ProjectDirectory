package com.ht.idempotencydemo.config;

import com.ht.idempotencydemo.domain.InterceptProperties;
import com.ht.idempotencydemo.intercept.HeaderIntercept;
import com.ht.idempotencydemo.intercept.RepeatIntercept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author mbql
 * 注册拦截器
 * @date 2020/3/28 14:56
 */
@Configuration
@EnableConfigurationProperties(InterceptProperties.class)
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private InterceptProperties interceptProperties;
    @Autowired
    private HeaderIntercept headerIntercept;
    @Autowired
    private RepeatIntercept repeatIntercept;

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headerIntercept).excludePathPatterns(interceptProperties.getHeadInterceptExclude());
        registry.addInterceptor(repeatIntercept).excludePathPatterns(interceptProperties.getRepeatInterceptExclude());
    }
}
