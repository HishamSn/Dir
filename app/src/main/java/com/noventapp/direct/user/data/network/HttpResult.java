package com.noventapp.direct.user.data.network;


import android.support.annotation.StringRes;

import com.noventapp.direct.user.model.ErrorModel;


/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */
public class HttpResult<T> {

    private int code;
    private HttpCall<T> call;
    private T result;
    private int status;
    private ErrorModel error;
    private Throwable throwable;
    private int localizedMsg;
    private int errorIcon;
    private boolean hasRetry;
    private int retryMsg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HttpCall<T> getCall() {
        return call;
    }

    public void setCall(HttpCall<T> call) {
        this.call = call;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @HttpStatus
    public int getStatus() {
        return status;
    }

    public void setStatus(@HttpStatus int status) {
        this.status = status;
    }

    public ErrorModel getError() {
        return error;
    }

    public void setError(ErrorModel error) {
        this.error = error;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setLocalizedMsg(@StringRes int localizedMsg) {
        this.localizedMsg = localizedMsg;
    }

    @StringRes
    public int getLocalizedMsg() {
        return localizedMsg;
    }
}
