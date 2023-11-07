package com.alesgerov.urlShortener.service;

import com.alesgerov.urlShortener.dto.ShortenDto;
import com.alesgerov.urlShortener.model.Url;

/**
 * @author Alasgarov Tofig
 * @date 11/5/2023
 */
public interface ShortenerService {
    ShortenDto shortenUrl(ShortenDto shortenDto);

}
