package com.noventapp.direct.user.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

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


    public static void errorMessage(Context context, String title, String message) {
        errorMessage(context, title, message, false);
    }

    public static void errorMessage(Context context, String message, Boolean killActivity) {
        errorMessage(context, "Error", message, killActivity);
    }

    public static void warningMessage(Context context, String message) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setContentText(message)
                .show();
    }

    public static SweetAlertDialog progress(Context context) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#9c27b0"));
        pDialog.setTitleText(R.string.loading);
        pDialog.setCancelable(false);
        return pDialog;
    }


    public static void displayPromptForEnablingGPS(final Activity activity) {
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        boolean gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;

        LayoutInflater inflater2 = LayoutInflater.from(activity);
        final View locationDialog = inflater2.inflate(R.layout.dialog_location_permission
                , null);
        final AlertDialog dialog = new AlertDialog.Builder(activity).create();
        dialog.setView(locationDialog);
        locationDialog.findViewById(R.id.btn_yes).setOnClickListener(v -> {
            activity.startActivity(new Intent(action));
            dialog.dismiss();
        });

        locationDialog.findViewById(R.id.btn_no).setOnClickListener(v -> dialog.dismiss());
        if (!gpsStatus) {
            dialog.show();
        }

    }
}
