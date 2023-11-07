package com.alesgerov.urlShortener.service.impl;

import com.alesgerov.urlShortener.service.FilterService;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Alasgarov Tofig
 * @date 11/7/2023
 */
@Service
public class BloomFilterServiceImpl implements FilterService {

    private final RBloomFilter<String> shortUrlFilter;

    public BloomFilterServiceImpl(@Qualifier("shortUrlBloomFilter") RBloomFilter<String> shortUrlFilter) {
        this.shortUrlFilter = shortUrlFilter;
    }

    @Override
    public boolean isExists(String longUrl) {
        return shortUrlFilter.contains(longUrl);
    }

}
