package com.alesgerov.urlShortener.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alasgarov Tofig
 * @date 11/4/2023
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ShortenDto {
    private String longUrl;
    private String shortUrl;

    public ShortenDto(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
