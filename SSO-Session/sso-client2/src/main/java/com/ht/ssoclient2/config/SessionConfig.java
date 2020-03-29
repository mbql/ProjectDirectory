package com.ht.ssoclient2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author mbql
 * @date 2020/3/29 16:21
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 90)
public class SessionConfig {
}
