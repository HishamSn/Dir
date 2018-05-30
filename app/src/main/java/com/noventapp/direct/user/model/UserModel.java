package com.noventapp.direct.user.model;

import com.squareup.moshi.Json;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Hisham Snaimeh on 5/6/2018.
 * hish.sn.dev@gmail.com
 */
@RealmClass
public class UserModel implements RealmModel {

    @PrimaryKey
    @Json(name = "id")
    private Integer id;
    @Json(name = "token")
    private String token;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

}
