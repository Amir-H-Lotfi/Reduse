package com.site.rrus.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import static java.lang.String.*;

@Configuration
@EnableRedisRepositories(basePackages = "com.site.rrus")
@Slf4j
public class RedisDefaultConfiguration {

    public RedisDefaultConfiguration() {
        log.info(format("Class[%s] configured".getClass().getName()));
    }


}
