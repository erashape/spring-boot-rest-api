package com.health.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class ApiResponse<T> {
    private ResponseCode responseCode;
    private String message;
    private T data;

    private ApiResponse(ResponseCode responseCode) {
        this.bind(responseCode);
    }

    private ApiResponse(ResponseCode responseCode, T data) {
        this.bind(responseCode);
        this.data = data;
    }

    private void bind(ResponseCode responseCode) {
        this.responseCode = responseCode;
        this.message = responseCode.getMessage();
    }

    public static ApiResponse<String> ok() {
        return new ApiResponse<>(ResponseCode.OK);
    }

    public static ApiResponse<String> failed() {
        return new ApiResponse<>(ResponseCode.SERVER_ERROR);
    }

    public static <T> ApiResponse<T> result(T data) {
        return new ApiResponse<>(ResponseCode.OK, data);
    }
}
