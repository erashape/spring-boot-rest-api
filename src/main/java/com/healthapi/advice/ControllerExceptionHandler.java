package com.healthapi.advice;

import com.google.gson.Gson;
import com.healthapi.advice.exception.CustomException;
import com.healthapi.common.ResponseCode;
import com.healthapi.model.CommonResult;
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
    protected CommonResult exception(Exception e) {
        log.error("Exception", e);
        log.warn("Exception message:{}", e.getMessage());

        return responseService.responseResult(ResponseCode.SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    protected CommonResult customException(CustomException e) {
        log.warn("CustomException message/customMessage:[{}/{}]", e.getMessage(), e.getCustomMessage());

        log.info(e.getCode());
        log.info(new Gson().toJson(ResponseCode.getResponse(e.getCode())));

        if(StringUtils.hasText(e.getCustomMessage())) {
            return responseService.responseResult(ResponseCode.getResponse(e.getCode()), e.getCustomMessage());
        }

        return responseService.responseResult(ResponseCode.getResponse(e.getCode()));
    }
}
