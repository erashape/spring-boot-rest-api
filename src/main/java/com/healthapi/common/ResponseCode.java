package com.healthapi.common;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum ResponseCode {
    SUCCESS(200, "0000", ""),
    NOT_FOUND_USER(400, "H000", "Not Found User"),
    SERVER_ERROR(500, "9999", "Server Error");

    private final int status;
    private final String code;
    private final String message;

    ResponseCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public static ResponseCode getResponse(String code) {
        return Stream.of(values())
                .filter(responseCode -> responseCode.code.equals(code))
                .findFirst()
                .orElse(ResponseCode.SERVER_ERROR);
    }
}
