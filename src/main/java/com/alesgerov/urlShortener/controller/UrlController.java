package com.alesgerov.urlShortener.controller;

import com.alesgerov.urlShortener.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Tofig Alasgarov
 * @created 06.11.23
 */
@Controller
@RequiredArgsConstructor
@RequestMapping
public class UrlController {

    private final UrlService service;

    @GetMapping(value = "/{shortUrl}")
    public void method(
            @PathVariable String shortUrl,
            HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", service.getUrl(shortUrl));
        httpServletResponse.setStatus(301);
    }
}
