package com.ht.springbootsentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @author mbql
 * @date 2020/3/23 16:54
 */
@Service
public class TestService {

    /**
     * 对限流资源的保护
     * @param name
     * @return
     */
    @SentinelResource(value = "sayHello",blockHandler = "handleSayHello",fallback = "fallSayHello")
    public String sayHello(String name){
        //模拟抛出异常
        if (name.equals("mbql")){
            throw  new RuntimeException();
        }
        return "Hi---" + name;
    }

    /**
     * 达到限流被触发
     * @param name
     * @param b
     * @return
     */
    public String handleSayHello(String name, BlockException b){
        b.printStackTrace();
        return "流量已达到极限，被限流中..."+name;
    }

    /**
     * 发生其它异常触发
     * @param name
     * @param throwable
     * @return
     */
    public String fallSayHello(String name, Throwable throwable){
        throwable.printStackTrace();
        return "发生其它异常,请检查服务---"+name;
    }

}
