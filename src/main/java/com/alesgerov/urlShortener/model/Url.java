package com.alesgerov.urlShortener.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyGroup;
import org.springframework.context.annotation.Lazy;

/**
 * @author Alasgarov Tofig
 * @date 11/4/2023
 */
@Entity
@Table(name = "url_table")
@Getter
@ToString
public class Url {

    @Id
    private Long id;

    @Column
    private String shortUrl;

    @Column
    private String longUrl;
}
