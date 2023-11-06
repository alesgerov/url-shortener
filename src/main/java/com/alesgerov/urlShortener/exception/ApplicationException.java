package com.alesgerov.urlShortener.exception;

import com.alesgerov.urlShortener.constants.Errors;
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
public class ApplicationException  extends RuntimeException  {
    private HttpStatus httpStatus;
    private String message;
    private Errors errors;

    public ApplicationException(String message, Errors errors) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.message = message;
        this.errors = errors;
    }

    public ApplicationException(String message, Errors errors, HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = errors;
    }

    public ApplicationException(Errors errors) {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.errors = errors;
    }
}
