package com.noventapp.direct.user.constants;

/**
 * Created by Hisham Snaimeh on 5/6/2018.
 */

public class ApiConstants {


    public static final String BASE_URL = "http://206.189.150.2:8080/direct-1.0.0/api/v1/";
    // country
    public static final String GET_COUNTRY = "country";
    // contact
    public static final String GET_CONTACT_US = "contactNumbers";
    public static final String CREATE_FEEDBACK = "customer/{id}/feedback";

    // user
    public static final String USER_SIGN_UP = "customer";
    public static final String USER_LOGIN = "auth/login";
    public static final String FORGET_PASSWORD = "forgetPassword";

    //city
    public static final String GET_CITES = GET_COUNTRY + "/{id}/city";

    // validation

    public static final String VALIDATE_EMAIL = "validate/email";
    public static final String VALIDATE_PHONE = "validate/customer/phoneNumber";

    //user setting
    public static final String GET_USER_INFO = "customer/profile";
    public static final String UPDATE_USER_INFO = "customer/update";
    public static final String VALIDATE_USER_NAME = "validate/userName";
    public static final String CHANGE_PASSWORD = "customer/{id}/resetPassword";

    // address
    public static final String CREATE_ADDRESS = "address";
    public static final String DELETE_ADDRESS = "address/{id}";
    public static final String UPDATE_ADDRESS = "address/{id}";
    public static final String GET_ALL_ADDRESS = "customer/{id}/address/";
    public static final String GET_ADDRESS_INFO = "address/{id}";

    // filters and category
    public static final String GET_PRIME_FILTER = "filterCategory/prime";
    public static final String GET_FEATURED_CLIENT_FILTER = "client/isTopSelling";


}


