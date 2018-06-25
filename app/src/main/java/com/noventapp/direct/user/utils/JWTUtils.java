package com.noventapp.direct.user.utils;

import android.util.Base64;

import com.noventapp.direct.user.constants.AppConstants.TokenEnum;

import java.io.UnsupportedEncodingException;

public class JWTUtils {


    public static String[] decodeJWT(String EncodeString) throws Exception {

        String[] tokenSplit = EncodeString.split("\\.");
//        Log.d("", "Header " + getJson(splitstr[0]));
//        Log.d("", "Payload " + getJson(splitstr[1]));

        return tokenSplit;

    }

    public static String decodeJWT(String EncodeString, TokenEnum tokenType)
            throws Exception {
        String[] tokenSplit = EncodeString.split("\\.");

        switch (tokenType) {
            case Header:
                return getJson(tokenSplit[0]);

            case Payload:
                return getJson(tokenSplit[1]);
        }

        return "{'data':empty}";
    }


    public static String getJson(String EncodeString) throws UnsupportedEncodingException {

        byte[] decodebyte = Base64.decode(EncodeString, Base64.URL_SAFE);
        return new String(decodebyte, "UTF-8");

    }
}
