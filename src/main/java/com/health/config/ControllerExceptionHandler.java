package com.health.config;

import com.health.response.ApiException;
import com.health.response.ApiResponse;
import com.health.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ApiResponse<String> exception(Exception e) {
        log.error("Exception", e);
        log.warn("Exception message:[{}]", e.getMessage());

        return ApiResponse.failed();
    }

    @ExceptionHandler(ApiException.class)
    protected ApiResponse<ResponseCode> apiException(ApiException e) {
        log.info("Response Message:[{}]", e.getMessage());
        log.warn("Response message:[{}]", e.getMessage());

        return ApiResponse.result(e.getResponseCode());
    }
}
