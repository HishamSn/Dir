package com.noventapp.direct.user.utils;

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
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText(message)
                .show();

    }


    public static void warningMessage(Context context, String message) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setContentText(message)
                .show();
    }

}
