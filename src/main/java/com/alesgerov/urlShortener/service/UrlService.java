package com.alesgerov.urlShortener.service;

import com.alesgerov.urlShortener.dto.ShortenDto;
import com.alesgerov.urlShortener.model.Url;

/**
 * @author Alasgarov Tofig
 * @date 11/8/2023
 */
public interface UrlService {
    String getLongUrl(String shortUrl);
    String getShortUrl(String longUrl);

    Url createUrl(ShortenDto shortenDto);
}
