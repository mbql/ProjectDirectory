package com.ht.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RefreshScope
@EnableDiscoveryClient
public class NacosDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosDemoApplication.class, args);
    }

    @Value("${test.name}")
    private String name;

    @Value("${test.age}")
    private Integer age;

    /**
     * 测试配置
     * @return
     */
    @GetMapping("test")
    public String testConfig(){
        return name+" : "+age;
    }

}
