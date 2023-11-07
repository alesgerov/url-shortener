package com.alesgerov.urlShortener.service.impl;

import com.alesgerov.urlShortener.configuration.RedisConfiguration;
import com.alesgerov.urlShortener.constants.ResponseCodes;
import com.alesgerov.urlShortener.exception.ApplicationException;
import com.alesgerov.urlShortener.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Alasgarov Tofig
 * @date 11/7/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RedisServiceImpl<T> implements CacheService<T> {
    private final RedisConfiguration redisConfiguration;
    private final RedissonClient redisson;

    @Override
    public boolean saveResponse(String request, T t) {
        try {
            isRedisEnabled();
            log.info("Saving response in cache-server");
            redisson.getMapCache(redisConfiguration.getCacheKey()).put(request, t);
            log.info("Completed saving response in cache-server");
            return true;
        } catch (RedisException e) {
            log.error("Redis disabled");
        } catch (Exception e) {
            log.error("Error occurred while saving response in cache-server: {}", e);
        }
        return false;
    }

    @Override
    public T getResponse(String request) {
        try {
            isRedisEnabled();
            log.info("Getting the response from cache");
            Object response = redisson.getMapCache(redisConfiguration.getCacheKey()).get(request);
            if (response == null) {
                log.info("Response was not found in cache-server");
            } else {
                log.info("Got the response from cache-server");
                log.trace("Request: " + request);
                log.trace("Response: " + response);
            }
            return (T) response;
        } catch (RedisException e) {
            log.error("Redis disabled");
        } catch (Exception exc) {
            log.error("Error occurred while getting the response from cache-server", exc);
        }
        return null;
    }

    @Override
    public void flush() {
        try {
            isRedisEnabled();
            boolean delete = redisson.getBucket(redisConfiguration.getCacheKey()).delete();
            log.info("Completed flushing the cache-server and flush response is : {}", delete);
        } catch (RedisException e) {
            log.error("Redis disabled");
        } catch (Exception exc) {
            log.error("Error occurred while flushing the cache-server", exc);
        }
    }

    private void isRedisEnabled() {
        if (!redisConfiguration.isEnabled()) {
            throw new ApplicationException(
                    HttpStatus.BAD_REQUEST,
                    "Redis caching is disabled.",
                    ResponseCodes.REDIS_DISABLED
            );
        }
    }
}
