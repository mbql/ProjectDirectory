package com.ht.springcloudsentinelserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringcloudsentinelServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudsentinelServerApplication.class, args);
    }

}
