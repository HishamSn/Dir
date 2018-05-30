package com.noventapp.direct.user.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Hisham Snaimeh on 5/22/2018.
 * hish.sn.dev@gmail.com
 */
public class UrlUtil {


    public static void loadUrl(String url, Context context) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        Intent browserIntent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent3);
    }

    public static void loadImage(Context context, ImageView imageView, String url) {
        Picasso.with(context).load(url).into(imageView);

    }
}
