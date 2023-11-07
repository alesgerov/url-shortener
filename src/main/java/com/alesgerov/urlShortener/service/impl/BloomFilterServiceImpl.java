package com.alesgerov.urlShortener.service.impl;

import com.alesgerov.urlShortener.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.springframework.stereotype.Service;

/**
 * @author Alasgarov Tofig
 * @date 11/7/2023
 */
@Service
@RequiredArgsConstructor
public class BloomFilterServiceImpl implements FilterService {

    private final RBloomFilter<String> shortUrlFilter;

    @Override
    //TODO fix it
    public boolean isExists(String shortUrl) {
        return shortUrlFilter.contains(shortUrl);
    }

}
