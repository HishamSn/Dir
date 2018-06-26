package com.noventapp.direct.user.constants;

/**
 * Created by Hisham Snaimeh on 5/6/2018.
 */

public class ApiConstants {


    public static final String BASE_URL = "http://206.189.150.2:8080/direct-1.0.0/api/v1/";
    // country
    public static final String GET_COUNTRY = "country";

    // user
    public static final String USER_SIGN_UP = "customer";
    public static final String USER_LOGIN = "auth/login";

    //city
    public static final String GET_CITES = GET_COUNTRY + "/{id}/city";

    // validation

    public static final String VALIDATE_EMAIL = "validate/email";
    public static final String VALIDATE_PHONE = "validate/customer/phoneNumber";

    //user setting
    public static final String GET_USER_INFO = "customer/{id}";
    public static final String UPDATE_USER_INFO = "customer/{id}";
    public static final String VALIDATE_USER_NAME = "validate/UserName";

}


