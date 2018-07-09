package com.noventapp.direct.user.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.IntDef;

import com.noventapp.direct.user.ui.address.MyAddressActivity;
import com.noventapp.direct.user.ui.main.MainActivity;

public class ActivityUtil {

    public static final int MAIN_ACTIVITY = 0;
    public static final int LANGUAGE_ACTIVITY = 1;
    public static final int COUNTRY_ACTIVITY = 2;
    public static final int AREA_ACTIVITY = 3;
    public static final int LOGIN_ACTIVITY = 4;
    public static final int SIGN_UP_ACTIVITY = 5;
    public static final int VERIFICATION_ACTIVITY = 6;
    public static final int ADDRESS_ACTIVITY = 7;

    @IntDef({
            MAIN_ACTIVITY, LANGUAGE_ACTIVITY, COUNTRY_ACTIVITY, AREA_ACTIVITY,
            LOGIN_ACTIVITY, SIGN_UP_ACTIVITY, VERIFICATION_ACTIVITY, ADDRESS_ACTIVITY
    })
    @interface IntentType {
    }

    public static void startActivityCode(Context context, Class defaultClass, @IntentType int transitionActivity) {
        switch (transitionActivity) {

            default:
                context.startActivity(new Intent(context, defaultClass));
                break;
            case ADDRESS_ACTIVITY:
                context.startActivity(new Intent(context, MyAddressActivity.class));

                break;
            case AREA_ACTIVITY:
                break;
            case COUNTRY_ACTIVITY:
                break;
            case LANGUAGE_ACTIVITY:
                break;
            case LOGIN_ACTIVITY:
                break;
            case MAIN_ACTIVITY:
                context.startActivity(new Intent(context, MainActivity.class));

                break;
            case SIGN_UP_ACTIVITY:
                break;
            case VERIFICATION_ACTIVITY:
                break;
        }
        ((Activity) context).finish();
    }
}
