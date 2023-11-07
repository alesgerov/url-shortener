package com.alesgerov.urlShortener.handler;

import com.alesgerov.urlShortener.constants.ResponseCodes;
import com.alesgerov.urlShortener.dto.GenericResponse;
import com.alesgerov.urlShortener.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Alasgarov Tofig
 * @date 11/7/2023
 */
@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<GenericResponse> applicationException(ApplicationException applicationException) {
        log.error(String.valueOf(applicationException));
        Map<String, Object> body = composeResponse(
                applicationException.getMessage(),
                applicationException.getResponseCode()
        );

        return ResponseEntity.status(applicationException.getHttpStatus())
                .body(GenericResponse.failure(
                        applicationException.getHttpStatus().value(),
                        applicationException.getResponseCode().name(),
                        body)
                );

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request
    ) {
        GenericResponse<Void> response = GenericResponse
                .failure(status.value(),
                        ex.getMessage());


        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> response
                        .addValidationError(fieldError.getField(),
                                fieldError.getDefaultMessage()));

        return ResponseEntity.status(status)
                .body(response);
    }

    private Map<String, Object> composeResponse(String message, ResponseCodes responseCode) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("responseCode", responseCode.getResponseCode());
        body.put("response", responseCode.getResponse());
        if (StringUtils.isNotEmpty(message)) {
            body.put("message", message);
        }
        return body;
    }
}
