package com.alesgerov.urlShortener.service.impl;

import com.alesgerov.urlShortener.dto.ShortenDto;
import com.alesgerov.urlShortener.service.ShortenerService;
import com.alesgerov.urlShortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Alasgarov Tofig
 * @date 11/5/2023
 */
@Service
@RequiredArgsConstructor
public class ShortenerServiceImpl implements ShortenerService {

    private final BloomFilterServiceImpl bloomFilterService;
    private final UrlService urlService;

    @Override
    public ShortenDto shortenUrl(ShortenDto shortenDto) {
        boolean isExists = bloomFilterService.isExists(shortenDto.getShortUrl());
        String shortUrl;
        if (isExists) {
            shortUrl = urlService.getUrl(shortenDto.getShortUrl());
            if (shortUrl != null) {
                return new ShortenDto(shortUrl);
            }
        }
        return new ShortenDto(urlService.createUrl(shortenDto).getShortUrl());
    }

}
