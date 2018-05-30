package com.noventapp.direct.user.data.network;

/**
 * Created by Hisham Snaimeh on 4/12/2018.
 */

public interface HttpCall<T> {

    void cancel();

    void enqueue(HttpCallback<T> callback);

    HttpCall<T> cloneCall();
}
