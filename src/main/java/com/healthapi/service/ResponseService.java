package com.healthapi.service;

import com.healthapi.response.ResponseCode;
import com.healthapi.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseService {
    public <T> ApiResponse<T> responseResult(ResponseCode responseCode) {
        ApiResponse<T> result = this.init(responseCode);
        result.setMessage(responseCode.getMessage());
        return result;
    }

    public <T> ApiResponse<T> responseResult(ResponseCode responseCode, String message) {
        ApiResponse<T> result = this.init(responseCode);
        result.setMessage(message);
        return result;
    }

    public <T> ApiResponse<T> responseResult(ResponseCode responseCode, T data) {
        ApiResponse<T> result = this.init(responseCode);
        result.setMessage(responseCode.getMessage());
        result.setData(data);
        return result;
    }

    public <T> ApiResponse<T> responseResult(ResponseCode responseCode, T data, String message) {
        ApiResponse<T> result = this.init(responseCode);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    private <T> ApiResponse<T> init(ResponseCode responseCode) {
        ApiResponse<T> result = new ApiResponse<>();
        result.setStatus(responseCode.getStatus());
        result.setCode(responseCode.getCode());
        return result;
    }
}
