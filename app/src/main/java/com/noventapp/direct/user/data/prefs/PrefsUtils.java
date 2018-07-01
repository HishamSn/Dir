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
    private static final String COUNTRY = "country";
    private static final String FIRST_USE = "true";
    private static Double lat;
    private static Double lng;
    private static Integer countryId;

    private static PrefsUtils instance;
    private SharedPreferences prefs;

    private PrefsUtils() {
        prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
    }

    public static synchronized PrefsUtils getInstance() {
        if (instance == null) {
            instance = new PrefsUtils();
        }
        return instance;
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

    public boolean isFirstUse() {
        return prefs.getBoolean(TOKEN, true);
    }

    public void setFirstUse(boolean firstUse) {
        prefs.edit().putBoolean(FIRST_USE, firstUse).apply();
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

    public Integer getCountryId() {
        return prefs.getInt(COUNTRY, 0);
    }

    public void setCountryId(Integer countryId) {
        prefs.edit().putInt(COUNTRY, countryId).apply();
    }
}
