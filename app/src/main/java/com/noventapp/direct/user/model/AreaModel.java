package com.noventapp.direct.user.model;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;


@RealmClass
public class AreaModel implements RealmModel {

    @PrimaryKey
    @Json(name = "id")
    private Integer id;
    @Json(name = "areaNameAr")
    private String areaNameAr;
    @Json(name = "areaNameEn")
    private String areaNameEn;
    @Json(name = "latitude")
    private Double latitude;
    @Json(name = "longitude")
    private Double longitude;

    private String baseAreaName;

    public AreaModel() {
    }

    public String getBaseAreaName() {
        return LocalHelper.isLanguageEn() ? areaNameEn : areaNameAr;
    }

    public void setBaseAreaName(String baseAreaName) {
        this.baseAreaName = baseAreaName;
    }

    public AreaModel(String baseAreaName) {
        this.baseAreaName = baseAreaName;
    }

    public Integer getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getAreaNameAr() {
        return areaNameAr;
    }

    public String getAreaNameEn() {
        return areaNameEn;
    }
}
