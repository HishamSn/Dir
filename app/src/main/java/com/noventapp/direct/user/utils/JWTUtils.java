package com.noventapp.direct.user.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class JWTUtils {


    public static String[] decodeJWT(String EncodeString) throws Exception {

        String[] splitstr = EncodeString.split("\\.");
//        Log.d("", "Header " + getJson(splitstr[0]));
//        Log.d("", "Payload " + getJson(splitstr[1]));

        return splitstr;

    }


    public static String getJson(String EncodeString) throws UnsupportedEncodingException {

        byte[] decodebyte = Base64.decode(EncodeString, Base64.URL_SAFE);
        return new String(decodebyte, "UTF-8");

    }
}
