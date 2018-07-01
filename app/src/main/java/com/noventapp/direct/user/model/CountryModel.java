package com.noventapp.direct.user.model;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;

public class CountryModel implements RealmModel {

    @PrimaryKey
    @Json(name = "id")
    private Integer id;
    @Json(name = "countryNameEn")
    private String countryNameEn;
    @Json(name = "countryNameAr")
    private String countryNameAr;
    @Json(name = "countryCode")
    private String countryCode;
    @Json(name = "countryPhoneCode")
    private String countryPhoneCode;
    @Json(name = "iconUrl")
    private String iconUrl;
    private String baseCountyName;

    public String getBaseCountyName() {
        return LocalHelper.isLanguageEn() ? countryNameEn : countryNameAr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryNameEn() {
        return countryNameEn;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public String getCountryNameAr() {
        return countryNameAr;
    }

    public void setCountryNameAr(String countryNameAr) {
        this.countryNameAr = countryNameAr;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryPhoneCode() {
        return countryPhoneCode;
    }

    public void setCountryPhoneCode(String countryPhoneCode) {
        this.countryPhoneCode = countryPhoneCode;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
