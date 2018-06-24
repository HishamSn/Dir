package com.noventapp.direct.user.utils;

import com.squareup.moshi.Moshi;

public class MoshiUtil {

    private static Moshi instance;

    public static Moshi getInstance() {
        if (instance == null) {
            instance = new Moshi.Builder().build();
        }

        return instance;
    }
}
