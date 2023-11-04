package com.alesgerov.urlShortener.service.impl;

import com.alesgerov.urlShortener.dto.ShortenDto;
import com.alesgerov.urlShortener.repo.UrlRepo;
import com.alesgerov.urlShortener.service.ShortenerService;
import com.alesgerov.urlShortener.utils.UniqueIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Alasgarov Tofig
 * @date 11/5/2023
 */
@Service
@RequiredArgsConstructor
public class ShortenerServiceImpl implements ShortenerService {

    private final UrlRepo urlRepo;

    @Override
    public String shortenUrl(ShortenDto shortenDto) {
        var optionalUrl = urlRepo.findUrlByLongUrl(shortenDto.getLongUrl());
        if (optionalUrl.isEmpty()){
            var id= UniqueIdGenerator.generateId();

        }
        return optionalUrl.get().getShortUrl();
    }

    @Override
    public String getUrl(String shortUrl) {
        return null;
    }
}
