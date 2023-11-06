package com.alesgerov.urlShortener.service.impl;

import com.alesgerov.urlShortener.constants.Errors;
import com.alesgerov.urlShortener.dto.ShortenDto;
import com.alesgerov.urlShortener.exception.ApplicationException;
import com.alesgerov.urlShortener.model.Url;
import com.alesgerov.urlShortener.repo.UrlRepo;
import com.alesgerov.urlShortener.service.ShortenerService;
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

    private final UrlRepo urlRepo;

    @Override
    public String shortenUrl(ShortenDto shortenDto) {
        var optionalUrl = urlRepo.findUrlByLongUrl(shortenDto.getLongUrl());
        if (optionalUrl.isEmpty()) {
            var id = UniqueIdGenerator.generateId();
            var hashedUrl = HashingUtils.hashByBase62(id);
            Url url = new Url();

            url.setLongUrl(shortenDto.getLongUrl());
            url.setShortUrl(hashedUrl);
            url.setId(id);

            urlRepo.save(url);

            return hashedUrl;
        }
        return optionalUrl.get().getShortUrl();
    }

    @Override
    public String getUrl(String shortUrl) {
        return urlRepo.findLongUrlByShortUrl(shortUrl)
                .orElseThrow(() -> new ApplicationException(
                        "Url not found",
                        Errors.NOT_FOUND,
                        HttpStatus.NOT_FOUND
                ));
    }
}
