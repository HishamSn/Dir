package com.noventapp.direct.user.model;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

import java.util.List;

public class CityModel {

    @Json(name = "id")
    private Integer id;
    @Json(name = "cityNameAr")
    private String cityNameAr;
    @Json(name = "cityNameEn")
    private String cityNameEn;
    @Json(name = "areas")
    private List<AreaModel> areaList = null;

    private String baseCityName;


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
}
