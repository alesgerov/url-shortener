package com.alesgerov.urlShortener.service;

import com.alesgerov.urlShortener.dto.ShortenDto;
import com.alesgerov.urlShortener.model.Url;

/**
 * @author Alasgarov Tofig
 * @date 11/8/2023
 */
public interface UrlService {
    String getUrl(String shortUrl);

    Url createUrl(ShortenDto shortenDto);
}
