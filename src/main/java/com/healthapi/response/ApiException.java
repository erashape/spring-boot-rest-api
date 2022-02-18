package com.healthapi.response;

import lombok.Getter;

public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @Getter
    private String code;

    @Getter
    private String message;

    public ApiException(String code) {
        this.code = code;
    }

    public ApiException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public ApiException(ResponseCode responseCode) {
        super(responseCode.getMessage());
    }

    public ApiException(ResponseCode responseCode, String message) {
        super(message);
        this.message = message;
    }
}
