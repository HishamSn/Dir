package com.noventapp.direct.user.utils;

import android.util.Base64;

import com.noventapp.direct.user.constants.AppConstants.TokenEnum;

import java.io.UnsupportedEncodingException;

public class JwtUtils {


    public static String[] decodeJWT(String encodeString) throws Exception {

        String[] tokenSplit = encodeString.split("\\.");
//        Log.d("", "Header " + getJson(splitstr[0]));
//        Log.d("", "Payload " + getJson(splitstr[1]));

        return tokenSplit;

    }

    public static String decodeJWT(String encodeString, TokenEnum tokenType)
            throws Exception {
        String[] tokenSplit = encodeString.split("\\.");

        switch (tokenType) {
            case Header:
                return getJson(tokenSplit[0]);

            case Payload:
                return getJson(tokenSplit[1]);
        }

        return "{'data':empty}";
    }


    public static String getJson(String encodeString) throws UnsupportedEncodingException {

        byte[] decodebyte = Base64.decode(encodeString, Base64.URL_SAFE);
        return new String(decodebyte, "UTF-8");

    }
}
