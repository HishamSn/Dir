package com.noventapp.direct.user.model;

import com.squareup.moshi.Json;

/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */

public class BaseGenericWrapper<T> {

    @Json(name = "code")
    private int code;
    @Json(name = "status")
    private String status;
    @Json(name = "message")
    private String message;
    @Json(name = "size")
    private Integer size;

    @Json(name = "data")
    private T data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
