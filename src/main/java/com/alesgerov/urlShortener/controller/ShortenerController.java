package com.alesgerov.urlShortener.controller;

import com.alesgerov.urlShortener.dto.ShortenDto;
import com.alesgerov.urlShortener.service.ShortenerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alasgarov Tofig
 * @date 11/4/2023
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ShortenerController {

    private final ShortenerService service;

    @PostMapping("data/shorten")
    public ResponseEntity<String> shorten(@Valid @RequestBody ShortenDto shortenDto) {
        return ResponseEntity.ok(service.shortenUrl(shortenDto));
    }

}
