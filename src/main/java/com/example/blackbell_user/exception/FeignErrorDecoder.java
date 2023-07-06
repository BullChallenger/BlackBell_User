package com.example.blackbell_user.exception;

import com.example.blackbell_user.constant.ResultType;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400 :
                break;
            case 404:
                if (methodKey.contains("getOrders")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()),
                            "This Account's order is empty.");
                }
                break;
            default:
                throw new BaseException(ResultType.SYSTEM_ERROR);
        }
        return null;
    }
}
