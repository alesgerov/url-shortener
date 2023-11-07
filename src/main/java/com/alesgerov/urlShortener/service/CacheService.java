package com.alesgerov.urlShortener.service;

/**
 * @author Alasgarov Tofig
 * @date 11/7/2023
 */
public interface CacheService<T> {

    boolean saveResponse(String request, T t);

    T getResponse(String request);

    void flush();
}
