package com.ht.springbootsentinel.service;

import com.ht.springbootsentinel.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author mbql
 * @date 2020/3/23 18:31
 */
@FeignClient(name = "service-provider",fallback = EchoServiceFallback.class,configuration = FeignConfiguration.class)
public interface EchoService {

    @GetMapping("echo/{str}")
    String echo(@PathVariable("str") String str);

}
