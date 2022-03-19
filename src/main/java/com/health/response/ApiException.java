package com.health.response;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private ResponseCode responseCode;

    private String message;

    public ApiException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public ApiException(ResponseCode responseCode, Exception e) {
        super(e);
        this.responseCode = responseCode;
        this.message = e.getMessage();
    }

    public ApiException(ResponseCode responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public ApiException(ResponseCode responseCode, String message, Exception e) {
        super(e);
        this.responseCode = responseCode;
        this.message = message;
    }
}
