package com.ht.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mbql
 * @date 2020/3/23 15:15
 */
@Configuration
public class SentinelAspectConfiguration {

    /**
     * 注入切面配置
     * @return
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect(){
        return new SentinelResourceAspect();
    }
}
