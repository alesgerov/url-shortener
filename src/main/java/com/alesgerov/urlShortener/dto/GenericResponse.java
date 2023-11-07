package com.alesgerov.urlShortener.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Alasgarov Tofig
 * @date 11/7/2023
 */
@Getter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<R> {
    @JsonFormat(pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime timestamp;
    private Integer status;
    private String key;
    private String message;
    private R data;
    private List<ValidationError> errors;


    public static <R> GenericResponse<R> success(R data, String key) {
        return new GenericResponseBuilder<R>()
                .status(HttpStatus.OK.value())
                .key(key)
                .data(data)
                .build();
    }

    public static <R> GenericResponse<R> success(R data) {
        return new GenericResponseBuilder<R>()
                .status(HttpStatus.OK.value())
                .key("SUCCESS")
                .data(data)
                .build();
    }

    public static GenericResponse<String> success(String key) {
        return new GenericResponseBuilder<String>()
                .status(HttpStatus.OK.value())
                .key(key)
                .build();
    }

    public static GenericResponse<Void> failure(int httpStatusCode, String key, String message) {
        return new GenericResponseBuilder<Void>()
                .timestamp(OffsetDateTime.now())
                .status(httpStatusCode)
                .key(key)
                .message(message)
                .build();
    }

    public static <R> GenericResponse<R> failure(int httpStatusCode, String key, R data) {
        return new GenericResponseBuilder<R>()
                .timestamp(OffsetDateTime.now())
                .status(httpStatusCode)
                .key(key)
                .data(data)
                .build();
    }

    public static <R> GenericResponse<R> failure(String message, R data) {
        return new GenericResponseBuilder<R>()
                .timestamp(OffsetDateTime.now())
                .message(message)
                .data(data)
                .build();
    }

    public static GenericResponse<Void> failure(int httpStatusCode, String key) {
        return new GenericResponseBuilder<Void>()
                .timestamp(OffsetDateTime.now())
                .status(httpStatusCode)
                .key(key)
                .build();
    }

    public void addValidationError(String field, String message) {
        if (Objects.isNull(errors)) errors = new ArrayList<>();
        errors.add(new ValidationError(field, message));
    }

    @Data
    @AllArgsConstructor
    private static class ValidationError {
        String field;
        String message;
    }

}
