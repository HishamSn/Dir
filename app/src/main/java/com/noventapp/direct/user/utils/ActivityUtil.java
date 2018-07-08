package com.noventapp.direct.user.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.noventapp.direct.user.ui.address.MyAddressActivity;

import static com.noventapp.direct.user.constants.AppConstants.TO_ADDRESS_ACTIVITY;

public class ActivityUtil {


    public static void startActivityCode(Context context, Class defaultClass, int transitionActivity) {
        switch (transitionActivity) {
            case TO_ADDRESS_ACTIVITY:
                context.startActivity(new Intent(context, MyAddressActivity.class));
                break;

            default:
                context.startActivity(new Intent(context, defaultClass));
                break;
        }
        ((Activity) context).finish();
    }
}
