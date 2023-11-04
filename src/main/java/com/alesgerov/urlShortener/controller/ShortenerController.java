package com.alesgerov.urlShortener.controller;

import com.alesgerov.urlShortener.dto.ShortenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alasgarov Tofig
 * @date 11/4/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ShortenerController {

    @PostMapping("data/shorten")
    public String shorten(@Valid @RequestBody ShortenDto shortenDto) {
        return "shortUrl";
    }

    @GetMapping("shortUrl")
    public String getShortUrl() {
        return "shortUrl";
    }
}
