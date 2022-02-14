package com.healthapi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CommonResult<T> {
    private int status;

    private String code;

    private String message;

    private T data;
}
