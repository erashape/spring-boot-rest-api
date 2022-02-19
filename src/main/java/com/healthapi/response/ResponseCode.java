package com.healthapi.response;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum ResponseCode {
    OK("0000", "정상 처리 되었습니다."),
    NOT_FOUND_USER("H000", "등록된 유저가 없습니다."),
    EXIST_USER("H001", "이미 등록된 유저입니다."),
    SERVER_ERROR( "H999", "시스템 오류입니다. 관리자에게 문의해주세요.");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
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
