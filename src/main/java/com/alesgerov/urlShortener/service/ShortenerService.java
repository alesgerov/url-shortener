package com.alesgerov.urlShortener.service;

import com.alesgerov.urlShortener.dto.ShortenDto;

/**
 * @author Alasgarov Tofig
 * @date 11/5/2023
 */
public interface ShortenerService {
    String shortenUrl(ShortenDto shortenDto);

    String getUrl(String shortUrl);
}
