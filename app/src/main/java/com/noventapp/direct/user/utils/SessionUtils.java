package com.noventapp.direct.user.utils;

import android.annotation.SuppressLint;

import com.noventapp.direct.user.data.db.DBHelper;
import com.noventapp.direct.user.data.prefs.PrefsUtils;
import com.noventapp.direct.user.model.UserModel;


/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */
public class SessionUtils {

    private UserModel user;
    private static SessionUtils instance;

    public static synchronized SessionUtils getInstance() {
        if (instance == null) {
            instance = new SessionUtils();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    private SessionUtils() {
        user = DBHelper.getInstance().getFirst(UserModel.class);
    }

    public void login(UserModel user) {
        this.user = user;
        DBHelper.getInstance().insertOrUpdate(user);
        PrefsUtils.getInstance().setLogin(true);
        PrefsUtils.getInstance().setToken(user.getToken());
    }

    public UserModel getUser() {
        return user;
    }

    public void logout() {
        DBHelper.getInstance().deleteAll(UserModel.class);
        PrefsUtils.getInstance().setToken("");
        PrefsUtils.getInstance().setLogin(false);
    }

    public boolean isLogin() {
        return PrefsUtils.getInstance().isLogin();
    }

}
