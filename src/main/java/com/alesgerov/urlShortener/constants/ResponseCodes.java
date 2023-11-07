package com.alesgerov.urlShortener.constants;

/**
 * @author Tofig Alasgarov
 * @created 06.11.23
 */
public enum ResponseCodes {
    NOT_FOUND(404, "Not found");

    private final int responseCode;
    private final String response;

    ResponseCodes(int responseCode, String response) {
        this.responseCode = responseCode;
        this.response = response;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponse() {
        return response;
    }
}
