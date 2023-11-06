package com.alesgerov.urlShortener.controller;

import com.alesgerov.urlShortener.service.ShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Tofig Alasgarov
 * @created 06.11.23
 */
@Controller
@RequiredArgsConstructor
@RequestMapping
public class UrlController {

    private final ShortenerService service;

    @GetMapping(value = "/{shortUrl}")
    public void method(
            @PathVariable String shortUrl,
            HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", service.getUrl(shortUrl));
        httpServletResponse.setStatus(301);
    }
}
