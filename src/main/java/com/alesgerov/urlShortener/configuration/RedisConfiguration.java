package com.alesgerov.urlShortener.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Alasgarov Tofig
 * @date 11/7/2023
 */
@Component
@ConfigurationProperties("application.redis")
@Data
public class RedisConfiguration {
    private String redisUrl;
    private Integer expireSeconds;
    private boolean enabled;
    private String cacheKey;

}