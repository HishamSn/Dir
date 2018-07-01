package com.noventapp.direct.user.utils;

import android.databinding.BindingAdapter;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Hisham Snaimeh on 7/1/2018.
 * hish.sn.dev@gmail.com
 */
public class BindingUtils {

    public BindingUtils() {
    }

    @BindingAdapter("bindingSrcCompat")
    public static void setImage(ImageView image, @DrawableRes int res) {
        Picasso.with(image.getContext()).load(res).fit().into(image);
    }
}
