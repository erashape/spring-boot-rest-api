package com.healthapi.advice.exception;

import com.healthapi.common.ResponseCode;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String code;
    private ResponseCode responseCode;

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public CustomException(String code) {
        super(ResponseCode.getResponse(code).getMessage());
        this.code = code;
        this.responseCode = ResponseCode.getResponse(code);
    }

    public CustomException(String code, Throwable cause) {
        super(ResponseCode.getResponse(code).getMessage(), cause);
        this.code = code;
        this.responseCode = ResponseCode.getResponse(code);
    }

    public CustomException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public CustomException(ResponseCode responseCode, Throwable cause) {
        super(responseCode.getMessage(), cause);
        this.responseCode = responseCode;
    }
}
