package com.noventapp.direct.user.data.db;


/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */


import com.noventapp.direct.user.common.MyApplication;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class DBHelper {

    private static DBHelper instance;

    private DBHelper() {
    }

    public static synchronized DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    public <T extends RealmModel> RealmResults<T> getAll(Class<T> clazz) {
        return MyApplication.getInstance().getRealm().where(clazz).findAll();
    }

    public <T extends RealmModel> T getFirst(Class<T> clazz) {
        return MyApplication.getInstance().getRealm().where(clazz).findFirst();
    }

    public <T extends RealmModel> T getFirst(Class<T> clazz, String col, String value) {
        return MyApplication.getInstance().getRealm().where(clazz).equalTo(col, value).findFirst();
    }

    public <T extends RealmModel> RealmResults<T> getById(Class<T> clazz, String col, boolean val) {
        return MyApplication.getInstance().getRealm().where(clazz).equalTo(col, val).findAll();
    }

    public <T extends RealmModel> boolean updateById(Class<T> clazz, String col, String val, T t) {
        try {
            T t1 = MyApplication.getInstance().getRealm().where(clazz).equalTo(col, val).findFirst();
            MyApplication.getInstance().getRealm().beginTransaction();
            t1 = t;
            MyApplication.getInstance().getRealm().commitTransaction();


            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public <T extends RealmModel> boolean deleteById(Class<T> clazz, String col, String val) {
        try {
            T t1 = MyApplication.getInstance().getRealm().where(clazz).equalTo(col, val).findFirst();
            MyApplication.getInstance().getRealm().beginTransaction();
            RealmObject.deleteFromRealm(t1);
            MyApplication.getInstance().getRealm().commitTransaction();


            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public void deleteAll(Class<? extends RealmModel> clazz) {
        RealmResults realmResults = getAll(clazz);
        if (realmResults != null && realmResults.size() > 0) {
            MyApplication.getInstance().getRealm().beginTransaction();
            realmResults.deleteAllFromRealm();
            MyApplication.getInstance().getRealm().commitTransaction();
        }
    }

    public <T extends RealmModel> void insertOrUpdate(List<T> clazz) {
        MyApplication.getInstance().getRealm().beginTransaction();
        MyApplication.getInstance().getRealm().copyToRealmOrUpdate(clazz);
        MyApplication.getInstance().getRealm().commitTransaction();
    }

    public <T extends RealmModel> void insertOrUpdate(T clazz) {
        MyApplication.getInstance().getRealm().beginTransaction();
        MyApplication.getInstance().getRealm().copyToRealmOrUpdate(clazz);
        MyApplication.getInstance().getRealm().commitTransaction();
    }

    public <T extends RealmModel> void insert(List<T> clazz) {
        MyApplication.getInstance().getRealm().beginTransaction();
        MyApplication.getInstance().getRealm().copyToRealm(clazz);
        MyApplication.getInstance().getRealm().commitTransaction();
    }

    public <T extends RealmModel> void insert(T clazz) {
        MyApplication.getInstance().getRealm().beginTransaction();
        MyApplication.getInstance().getRealm().copyToRealm(clazz);
        MyApplication.getInstance().getRealm().commitTransaction();
    }

    public Realm getRealm() {
        return MyApplication.getInstance().getRealm();
    }
}
