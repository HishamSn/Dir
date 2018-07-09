package com.noventapp.direct.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;


public class AreaModel implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.areaNameAr);
        dest.writeString(this.areaNameEn);
        dest.writeString(this.baseAreaName);
    }

    protected AreaModel(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.areaNameAr = in.readString();
        this.areaNameEn = in.readString();
        this.baseAreaName = in.readString();
    }

    @Override
    public String toString() {
        return "AreaModel{" +
                "id=" + id +
                ", areaNameAr='" + areaNameAr + '\'' +
                ", areaNameEn='" + areaNameEn + '\'' +
                ", baseAreaName='" + baseAreaName + '\'' +
                '}';
    }

    public static final Parcelable.Creator<AreaModel> CREATOR = new Parcelable.Creator<AreaModel>() {
        @Override
        public AreaModel createFromParcel(Parcel source) {
            return new AreaModel(source);
        }

        @Override
        public AreaModel[] newArray(int size) {
            return new AreaModel[size];
        }
    };
}
