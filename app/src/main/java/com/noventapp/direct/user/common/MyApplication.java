package com.noventapp.direct.user.common;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */


//* when application is run for the first time *//

public class MyApplication extends Application {

    private static MyApplication instance;
    private Realm realm;

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(this);

    }

    public synchronized Realm getRealm() {
        if (realm == null) {
            RealmConfiguration configuration = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            realm = Realm.getInstance(configuration);
        }
        return realm;
    }
}
