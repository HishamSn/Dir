package com.noventapp.direct.user.model;

/**
 * Created by Hisham Snaimeh on 5/13/2018.
 * hish.sn.dev@gmail.com
 */
public class ErrorModel {

    private String message;
    private int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
