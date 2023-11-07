package com.alesgerov.urlShortener.service.impl;

import com.alesgerov.urlShortener.constants.ResponseCodes;
import com.alesgerov.urlShortener.dto.ShortenDto;
import com.alesgerov.urlShortener.exception.ApplicationException;
import com.alesgerov.urlShortener.model.Url;
import com.alesgerov.urlShortener.repo.UrlRepo;
import com.alesgerov.urlShortener.service.ShortenerService;
import com.alesgerov.urlShortener.service.UrlService;
import com.alesgerov.urlShortener.utils.HashingUtils;
import com.alesgerov.urlShortener.utils.UniqueIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
