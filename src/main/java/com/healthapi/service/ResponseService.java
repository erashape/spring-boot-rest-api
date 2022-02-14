package com.healthapi.service;

import com.healthapi.common.ResponseCode;
import com.healthapi.dto.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseService {
    public <T> CommonResult<T> responseResult(ResponseCode responseCode) {
        CommonResult<T> result = this.init(responseCode);
        result.setMessage(responseCode.getMessage());
        return result;
    }

    public <T> CommonResult<T> responseResult(ResponseCode responseCode, String message) {
        CommonResult<T> result = this.init(responseCode);
        result.setMessage(message);
        return result;
    }

    public <T> CommonResult<T> responseResult(ResponseCode responseCode, T data) {
        CommonResult<T> result = this.init(responseCode);
        result.setMessage(responseCode.getMessage());
        result.setData(data);
        return result;
    }

    public <T> CommonResult<T> responseResult(ResponseCode responseCode, T data, String message) {
        CommonResult<T> result = this.init(responseCode);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    private <T> CommonResult<T> init(ResponseCode responseCode) {
        CommonResult<T> result = new CommonResult<>();
        result.setStatus(responseCode.getStatus());
        result.setCode(responseCode.getCode());
        return result;
    }
}
