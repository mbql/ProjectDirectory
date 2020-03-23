package com.ht.springcloudsentinelclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudsentinelClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudsentinelClientApplication.class, args);
    }

}
