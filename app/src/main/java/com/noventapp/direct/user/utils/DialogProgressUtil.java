package com.noventapp.direct.user.utils;

import android.graphics.Color;

import com.noventapp.direct.user.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DialogProgressUtil {

    private static SweetAlertDialog instance;

    public static SweetAlertDialog getInstance(Boolean isFirstLaunch) {
        if (instance == null || isFirstLaunch) {
            instance = new SweetAlertDialog(ContextHolder.getDefaultContext(),
                    SweetAlertDialog.PROGRESS_TYPE);
            instance.getProgressHelper().setBarColor(Color.parseColor("#9c27b0"));
            instance.setTitleText(R.string.loading);
            instance.setCancelable(false);
        }
        return instance;
    }


    public static SweetAlertDialog getInstance() {
        return instance;
    }

}
