package com.alesgerov.urlShortener.constants;

import lombok.Getter;

/**
 * @author Tofig Alasgarov
 * @created 06.11.23
 */
@Getter
public enum Errors {
    NOT_FOUND("Not found", "0404");
    private final String errorCode;
    private String message;

    Errors(String errorCode) {
        this.errorCode = errorCode;
    }

    Errors(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
