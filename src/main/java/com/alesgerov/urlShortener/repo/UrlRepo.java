package com.alesgerov.urlShortener.repo;

import com.alesgerov.urlShortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Alasgarov Tofig
 * @date 11/5/2023
 */
public interface UrlRepo extends JpaRepository<Url, Long> {
    Optional<Url> findUrlByLongUrl(String longUrl);

    @Query(nativeQuery = true, value = "select long_url from url_table where short_url=:shortUrl")
    Optional<String> findLongUrlByShortUrl(@Param("shortUrl") String shortUrl);
}
