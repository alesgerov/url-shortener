package com.alesgerov.urlShortener.configuration;

import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alasgarov Tofig
 * @date 11/7/2023
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final RedisConfiguration redissonConfig;

    @Bean
    public Config getConfig() {
        Config config = new Config();
        config.useSingleServer().setAddress(redissonConfig.getRedisUrl());
        return config;
    }

    @Bean
    public RedissonClient getRedisClient(Config config) {
        return Redisson.create(config);
    }

    @Bean
    public RBloomFilter<String> createBloomFilter() {
        RBloomFilter<String> stringRBloomFilter = getRedisClient(getConfig())
                .getBloomFilter("shortUrls");

        //Set expectedInsertions and falseProbability
        stringRBloomFilter.tryInit(99999, 0.001);


        return stringRBloomFilter;
    }
}
