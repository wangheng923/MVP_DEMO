package com.example.wangheng2.ak47.entity;

/**
 * Created by wangheng2 on 2018/3/16.
 */

public class ResultDto<T> {
    private String message;
    private String result;
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
