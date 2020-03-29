package com.ht.ssoclient1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author mbql
 * @date 2020/3/29 16:21
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class SessionConfig {
}
