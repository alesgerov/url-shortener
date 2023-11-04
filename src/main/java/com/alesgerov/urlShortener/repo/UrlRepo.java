package com.alesgerov.urlShortener.repo;

import com.alesgerov.urlShortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Alasgarov Tofig
 * @date 11/5/2023
 */
public interface UrlRepo extends JpaRepository<Url, Long> {
    Optional<Url> findUrlByLongUrl(String longUrl);
}
