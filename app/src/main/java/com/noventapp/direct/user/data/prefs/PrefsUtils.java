package com.noventapp.direct.user.data.prefs;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.noventapp.direct.user.common.MyApplication;




/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */

public class PrefsUtils {

    private static final String LOGIN = "login";
    private static final String TOKEN = "token";
    private static Double lat;
    private static Double lng;
    private static Integer countryId;

    private static PrefsUtils instance;
    private SharedPreferences prefs;

    public PrefsUtils() {
        prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
    }

    public static synchronized PrefsUtils getInstance() {
        if (instance == null) {
            instance = new PrefsUtils();
        }
        return instance;
    }

    public String getToken() {
        return prefs.getString(TOKEN, "");
    }

    public void setToken(String token) {
        prefs.edit().putString(TOKEN, token).apply();
    }

    public boolean isLogin() {
        return prefs.getBoolean(LOGIN, false);
    }

    public void setLogin(boolean login) {
        prefs.edit().putBoolean(LOGIN, login).apply();
    }

    public static Double getLat() {
        return lat;
    }

    public static void setLat(Double lat) {
        PrefsUtils.lat = lat;
    }

    public static Double getLng() {
        return lng;
    }

    public static void setLng(Double lng) {
        PrefsUtils.lng = lng;
    }

    public static Integer getCountryId() {
        return countryId;
    }

    public static void setCountryId(Integer countryId) {
        PrefsUtils.countryId = countryId;
    }
}
