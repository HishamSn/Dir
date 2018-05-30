package com.noventapp.direct.user.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Tasneem-NoventApp on 5/17/2018.
 */

public class FontUtil {
    private static Typeface customFont, customFontButton;

    public static void overrideFonts(final Context context, final View view) {
        if (view == null) {
            return;
        }
        try {
            if (LocalHelper.isLanguageEn()) {
                customFont = Typeface.createFromAsset(context.getAssets(), "Quicksand-Regular.ttf");
                customFontButton = Typeface.createFromAsset(context.getAssets(), "Quicksand-Bold.ttf");
            } else {
                customFont = Typeface.createFromAsset(context.getAssets(), "Cairo-Regular.ttf");
                customFontButton = Typeface.createFromAsset(context.getAssets(), "Cairo-Bold.ttf");
            }

            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View child = viewGroup.getChildAt(i);
                    overrideFonts(context, child);
                }
            } else if (view instanceof TextView) {
                ((TextView) view).setTypeface(customFont);
            } else if (view instanceof EditText) {
                ((EditText) view).setTypeface(customFont);
            } else if (view instanceof Button) {
                ((Button) view).setTypeface(customFontButton);
            }

        } catch (Exception e) {
        }
    }

}
