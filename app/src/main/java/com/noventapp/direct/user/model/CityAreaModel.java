package com.noventapp.direct.user.model;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;


@RealmClass
public class CityAreaModel implements RealmModel {

    @PrimaryKey
    @Json(name = "id")
    private Integer id;
    @Json(name = "cityNameAr")
    private String cityNameAr;
    @Json(name = "cityNameEn")
    private String cityNameEn;
    @Json(name = "areaNameAr")
    private String areaNameAr;
    @Json(name = "areaNameEn")
    private String areaNameEn;
    @Json(name = "latitude")
    private Double latitude;
    @Json(name = "longitude")
    private Double longitude;

    private String baseAreaName;
    private String baseCityName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityNameAr() {
        return cityNameAr;
    }

    public void setCityNameAr(String cityNameAr) {
        this.cityNameAr = cityNameAr;
    }

    public String getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(String cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public String getAreaNameAr() {
        return areaNameAr;
    }

    public void setAreaNameAr(String areaNameAr) {
        this.areaNameAr = areaNameAr;
    }

    public String getAreaNameEn() {
        return areaNameEn;
    }

    public void setAreaNameEn(String areaNameEn) {
        this.areaNameEn = areaNameEn;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getBaseAreaName() {
        return LocalHelper.isLanguageEn() ? areaNameEn : areaNameAr;
    }

    public String getBaseCityName() {
        return LocalHelper.isLanguageEn() ? cityNameEn : cityNameAr;
    }

}
