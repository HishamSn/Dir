package com.noventapp.direct.user.model;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

public class AmenitiesModel {
    @Json(name = "id")
    private Integer id;
    @Json(name = "amenitiesNameEn")
    private String amenitiesNameEn;
    @Json(name = "amenitiesNameAr")
    private String amenitiesNameAr;
    @Json(name = "icon")
    private String icon;
    private String baseAmenitiesName;

    public String getBaseAmenitiesName() {
        return LocalHelper.isLanguageEn() ? amenitiesNameEn : amenitiesNameAr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmenitiesNameEn() {
        return amenitiesNameEn;
    }

    public void setAmenitiesNameEn(String amenitiesNameEn) {
        this.amenitiesNameEn = amenitiesNameEn;
    }

    public String getAmenitiesNameAr() {
        return amenitiesNameAr;
    }

    public void setAmenitiesNameAr(String amenitiesNameAr) {
        this.amenitiesNameAr = amenitiesNameAr;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
