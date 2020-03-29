package com.ht.ssoclient2.config;

import com.ht.ssoclient2.filter.ServerSsoFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author mbql
 * @date 2020/3/29 16:20
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ServerSsoFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("LogCostFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
