package com.noventapp.direct.user.utils;

import android.app.Activity;
import android.content.Context;

import com.noventapp.direct.user.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Hisham Snaimeh on 4/26/2018.
 * hish.sn.dev@gmail.com
 */

public class DialogUtil {

    public static void serverError(Context context) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(R.string.sorry)
                .setContentText(context.getString(R.string.server_error))
                .show();
    }

    public static void serverError(Context context, String msg) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(R.string.sorry)
                .setContentText(msg)
                .show();
    }

    public static void successMessage(Context context, String message) {
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setContentText(message)
                .show();
    }

    public static void errorMessage(Context context, String message) {
        if (message == null) {
            message = "Null";
        }

        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Sorry...")
                .setContentText(message)
                .show();

    }

    public static void errorMessage(Context context, String title, String message,
                                    Boolean killActivity) {
        if (title == null || message == null) {
            title = "...";
            message = "Null";
        }

        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Sorry " + title)
                .setContentText(message)
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    if (killActivity) {
                        ((Activity) context).finish();
                    }
                })
                .show();

    }


    public static void warningMessage(Context context, String message) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setContentText(message)
                .show();
    }

}
