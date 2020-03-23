package com.ht.springbootsentinel.config;

import com.ht.springbootsentinel.service.EchoServiceFallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author mbql
 * @date 2020/3/23 18:32
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public EchoServiceFallback serviceFallback(){
        return new EchoServiceFallback();
    }

}
