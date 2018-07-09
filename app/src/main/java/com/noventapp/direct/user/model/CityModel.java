package com.noventapp.direct.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

import java.util.List;

public class CityModel implements Parcelable {

    @Json(name = "id")
    private Integer id;
    @Json(name = "cityNameAr")
    private String cityNameAr;
    @Json(name = "cityNameEn")
    private String cityNameEn;
    @Json(name = "areas")
    private List<AreaModel> areaList = null;

    private String baseCityName;

    public CityModel() {
    }

    public CityModel(String cityName, List<AreaModel> districtList) {
        this.baseCityName = cityName;
        this.areaList = districtList;
    }


    public List<AreaModel> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaModel> areaList) {
        this.areaList = areaList;
    }

    public Integer getId() {
        return id;
    }

    public String getBaseCityName() {
        if (baseCityName == null) {
            baseCityName = LocalHelper.isLanguageEn() ? cityNameEn : cityNameAr;
        }
        return baseCityName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.cityNameAr);
        dest.writeString(this.cityNameEn);
        dest.writeString(this.baseCityName);
    }

    protected CityModel(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.cityNameAr = in.readString();
        this.cityNameEn = in.readString();
        this.baseCityName = in.readString();
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

    public void setBaseCityName(String baseCityName) {
        this.baseCityName = baseCityName;
    }

    public static final Parcelable.Creator<CityModel> CREATOR = new Parcelable.Creator<CityModel>() {
        @Override
        public CityModel createFromParcel(Parcel source) {
            return new CityModel(source);
        }

        @Override
        public CityModel[] newArray(int size) {
            return new CityModel[size];
        }
    };
}
