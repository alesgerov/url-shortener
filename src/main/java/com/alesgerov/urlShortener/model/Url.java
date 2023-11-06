package com.alesgerov.urlShortener.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Alasgarov Tofig
 * @date 11/4/2023
 */
@Entity
@Table(name = "url_table")
@Getter
@ToString
@Setter
public class Url {

    @Id
    private Long id;

    @Column
    private String shortUrl;

    @Column(length = 100000)
    @Basic(fetch = FetchType.LAZY)
    //TODO make it for big datas also
    private String longUrl;
}
