package com.alesgerov.urlShortener.service.impl;

import com.alesgerov.urlShortener.constants.ResponseCodes;
import com.alesgerov.urlShortener.dto.ShortenDto;
import com.alesgerov.urlShortener.exception.ApplicationException;
import com.alesgerov.urlShortener.model.Url;
import com.alesgerov.urlShortener.repo.UrlRepo;
import com.alesgerov.urlShortener.service.UrlService;
import com.alesgerov.urlShortener.utils.HashingUtils;
import com.alesgerov.urlShortener.utils.UniqueIdGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Alasgarov Tofig
 * @date 11/8/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final UrlRepo urlRepo;
    private final RedisServiceImpl<String> urlRedisService;
    private final RBloomFilter<String> shortUrlFilter;

    @Override
    public String getUrl(String shortUrl) {
        var longUrl = urlRedisService.getResponse(shortUrl);
        if (longUrl == null) {
            var optionalLongUrl = urlRepo.findLongUrlByShortUrl(shortUrl);
            if (optionalLongUrl.isPresent()){
                urlRedisService.saveResponse(shortUrl, longUrl);
                shortUrlFilter.add(shortUrl);
                return optionalLongUrl.get();
            }
            return optionalLongUrl.get();
        }
        return longUrl;
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
        urlRepo.save(url);
        shortUrlFilter.add(shortUrl);

        return url;
    }
}
