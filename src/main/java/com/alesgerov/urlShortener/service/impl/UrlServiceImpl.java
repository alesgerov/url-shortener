package com.alesgerov.urlShortener.service.impl;

import com.alesgerov.urlShortener.dto.ShortenDto;
import com.alesgerov.urlShortener.model.Url;
import com.alesgerov.urlShortener.repo.UrlRepo;
import com.alesgerov.urlShortener.service.UrlService;
import com.alesgerov.urlShortener.utils.HashingUtils;
import com.alesgerov.urlShortener.utils.UniqueIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Alasgarov Tofig
 * @date 11/8/2023
 */
@Service
@Slf4j
public class UrlServiceImpl implements UrlService {
    private final UrlRepo urlRepo;
    private final RedisServiceImpl<String> urlRedisService;
    private final RBloomFilter<String> shortUrlFilter;

    public UrlServiceImpl(UrlRepo urlRepo,
                          RedisServiceImpl<String> urlRedisService,
                          @Qualifier("shortUrlBloomFilter") RBloomFilter<String> shortUrlFilter) {
        this.urlRepo = urlRepo;
        this.urlRedisService = urlRedisService;
        this.shortUrlFilter = shortUrlFilter;
    }

    @Override
    public String getLongUrl(String shortUrl) {
        var longUrl = urlRedisService.getResponse(shortUrl);
        if (longUrl == null) {
            var optionalLongUrl = urlRepo.findLongUrlByShortUrl(shortUrl);
            if (optionalLongUrl.isPresent()) {
                urlRedisService.saveResponse(shortUrl, optionalLongUrl.get());
                shortUrlFilter.add(optionalLongUrl.get());
                return optionalLongUrl.get();
            }
            return null;
        }
        return longUrl;
    }

    @Override
    public String getShortUrl(String longUrl) {
        var shortUrl = urlRedisService.getResponse(longUrl);
        if (shortUrl == null) {
            var optionalShortUrl = urlRepo.findShortUrlByLongUrl(longUrl);
            if (optionalShortUrl.isPresent()) {
                urlRedisService.saveResponse(longUrl, optionalShortUrl.get());
                return optionalShortUrl.get();
            }
            return null;
        }
        return shortUrl;
    }

    @Override
    public Url createUrl(ShortenDto shortenDto) {
        var id = UniqueIdGenerator.generateId();
        var shortUrl = HashingUtils.hashByBase62(id);
        Url url = new Url();

        url.setLongUrl(shortenDto.getLongUrl());
        url.setShortUrl(shortUrl);
        url.setId(id);

        urlRedisService.saveResponse(shortUrl, shortenDto.getLongUrl());
        urlRedisService.saveResponse(shortenDto.getLongUrl(), shortUrl);

        urlRepo.save(url);
        shortUrlFilter.add(shortenDto.getLongUrl());
        return url;
    }
}
