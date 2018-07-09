package com.noventapp.direct.user.constants;

/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */

public class AppConstants {

    public static final String TAG = "NOVENT_APP";
    public static final Integer EMPTY_INTEGER_VALUE = -1;
    public static final String SELECTED_LANGUAGE = "Locale.Language";
    public static final String AR = "ar";
    public static final String EN = "en";


    public enum TokenEnum {Header, Payload}

    public static final int TO_ADDRESS_ACTIVITY = 5;
    public static final int TO_COUNTRY_ACTIVITY = 5;

    public enum ActivityTransactionEnum {LoginToAddress, LoginToOrder}
}
