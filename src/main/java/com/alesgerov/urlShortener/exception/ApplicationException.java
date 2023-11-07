package com.alesgerov.urlShortener.exception;

import com.alesgerov.urlShortener.constants.ResponseCodes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Tofig Alasgarov
 * @created 06.11.23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;
    private ResponseCodes responseCode;

    public ApplicationException(String message, ResponseCodes errors) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = message;
        this.responseCode = errors;
    }

    public ApplicationException(String message, ResponseCodes errors, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.responseCode = errors;
    }

    public ApplicationException(ResponseCodes errors) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.responseCode = errors;
    }
}
