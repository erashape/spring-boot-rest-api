package com.healthapi.advice;

import com.healthapi.advice.exception.CustomException;
import com.healthapi.common.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseCode> exception(Exception e) {
        log.error("Exception", e);
        log.warn("Exception message:{}", e.getMessage());

        ResponseCode responseCode = ResponseCode.SERVER_ERROR;

        return new ResponseEntity<>(responseCode, Objects.requireNonNull(HttpStatus.resolve(responseCode.getStatus())));
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ResponseCode> customException(CustomException e) {
        log.warn("CustomException message:{}", e.getMessage());
        ResponseCode responseCode = e.getResponseCode();

        return new ResponseEntity<>(responseCode, Objects.requireNonNull(HttpStatus.resolve(responseCode.getStatus())));
    }
}
