package com.healthapi.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ApiResponse<T> {
    private int status;

    private String code;

    private String message;

    private T data;
}
