package com.healthapi.advice.exception;

import com.healthapi.common.ResponseCode;
import lombok.Getter;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @Getter
    private String code;

    @Getter
    private String customMessage;

    public CustomException(String code) {
        this.code = code;
    }

    public CustomException(String code, String customMessage) {
        super(customMessage);
        this.code = code;
        this.customMessage = customMessage;
    }

    public CustomException(ResponseCode responseCode) {
        super(responseCode.getMessage());
    }

    public CustomException(ResponseCode responseCode, String customMessage) {
        super(customMessage);
        this.customMessage = customMessage;
    }
}
