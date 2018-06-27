package com.noventapp.direct.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

public class AreaModel  implements Parcelable {

    private String baseAreaName;


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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
