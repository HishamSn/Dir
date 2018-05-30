package com.noventapp.direct.user.utils;

import android.content.Context;

/**
 * Created by Hisham Snaimeh on 9/4/2017.
 */

public final class ContextHolder {

    private static Context defaultContext;

    private ContextHolder() {

    }

    public static Context getDefaultContext() {
        return defaultContext;
    }

    public static void setDefaultContext(Context defaultContext) {
        ContextHolder.defaultContext = defaultContext;
    }
}
