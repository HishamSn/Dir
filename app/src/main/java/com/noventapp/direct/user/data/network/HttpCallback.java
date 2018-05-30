package com.noventapp.direct.user.data.network;


public interface HttpCallback<T> {

    void onResult(HttpResult<T> result);
}
