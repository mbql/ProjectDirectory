package com.ht.springbootsentinel.controller;

import com.ht.springbootsentinel.service.EchoServiceFallback;
import com.ht.springbootsentinel.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mbql
 * @date 2020/3/23 16:56
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;
    @Autowired
    EchoServiceFallback echoServiceFallback;

    @GetMapping("hello/{name}")
    public  String apiHello(@PathVariable String name){
        return testService.sayHello(name);
    }

    @GetMapping("/echo/{str}")
    public  String echoFallback(@PathVariable String str){
        return echoServiceFallback.echo(str);
    }
}
