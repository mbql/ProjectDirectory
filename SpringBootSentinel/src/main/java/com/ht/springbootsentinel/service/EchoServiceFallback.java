package com.ht.springbootsentinel.service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author mbql
 * @date 2020/3/23 18:33
 */
public class EchoServiceFallback implements EchoService{
    @Override
    public String echo(@PathVariable String str) {
        return "echo fallback"+str;
    }
}
