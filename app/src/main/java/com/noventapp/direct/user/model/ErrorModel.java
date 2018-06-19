package com.noventapp.direct.user.model;

import com.squareup.moshi.Json;

/**
 * Created by Hisham Snaimeh on 5/13/2018.
 * hish.sn.dev@gmail.com
 */
public class ErrorModel {

    @Json(name = "error")
    private String title;
    private String message;
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
