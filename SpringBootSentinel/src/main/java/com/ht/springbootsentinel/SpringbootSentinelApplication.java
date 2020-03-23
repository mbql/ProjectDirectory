package com.ht.springbootsentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.ht.springbootsentinel")
public class SpringbootSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSentinelApplication.class, args);
    }

}
