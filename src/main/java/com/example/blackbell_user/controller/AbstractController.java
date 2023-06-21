package com.example.blackbell_user.controller;

import com.example.blackbell_user.dto.ResponseDTO;
import com.example.blackbell_user.dto.ResultObject;

public abstract class AbstractController {

    protected <T> ResponseDTO<T> ok() {
        return ok(null, ResultObject.getSuccess());
    }

    protected <T> ResponseDTO<T> ok(T data) {
        return ok(data, ResultObject.getSuccess());
    }

    protected <T> ResponseDTO<T> ok(T data, ResultObject result) {
        ResponseDTO<T> obj = new ResponseDTO<>();
        obj.setData(data);
        obj.setResult(result);

        return obj;
    }
}
