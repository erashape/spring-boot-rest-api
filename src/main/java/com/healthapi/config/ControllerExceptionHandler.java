package com.healthapi.config;

import com.healthapi.response.ApiException;
import com.healthapi.response.ResponseCode;
import com.healthapi.response.ApiResponse;
import com.healthapi.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {
    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    protected ApiResponse<ResponseCode> exception(Exception e) {
        log.error("Exception", e);
        log.warn("Exception message:[{}]", e.getMessage());

        return responseService.responseResult(ResponseCode.SERVER_ERROR);
    }

    @ExceptionHandler(ApiException.class)
    protected ApiResponse<ResponseCode> customException(ApiException e) {
        log.info("Response Message:[{}]", e.getMessage());
        log.warn("Response message:[{}]", e.getMessage());

        if(StringUtils.hasText(e.getMessage())) {
            return responseService.responseResult(ResponseCode.getResponse(e.getCode()), e.getMessage());
        }

        return responseService.responseResult(ResponseCode.getResponse(e.getCode()));
    }
}
