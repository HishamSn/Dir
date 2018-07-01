package com.noventapp.direct.user.model;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class CityModel implements RealmModel {

    @PrimaryKey
    @Json(name = "id")
    private Integer id;
    @Json(name = "cityNameAr")
    private String cityNameAr;
    @Json(name = "cityNameEn")
    private String cityNameEn;
    @Json(name = "areas")
    private RealmList<AreaModel> areaList = null;

    private String baseCityName;

    public CityModel() {
    }

    public CityModel(String cityName, RealmList<AreaModel> districtList) {
        this.baseCityName = cityName;
        this.areaList = districtList;
    }


    public List<AreaModel> getAreaList() {
        return areaList;
    }

    public void setAreaList(RealmList<AreaModel> areaList) {
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
}
