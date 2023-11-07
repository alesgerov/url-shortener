package com.alesgerov.urlShortener.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Alasgarov Tofig
 * @date 11/4/2023
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortenDto {
    private String longUrl;
    private String shortUrl;
}
