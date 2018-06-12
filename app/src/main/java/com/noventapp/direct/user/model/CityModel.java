package com.noventapp.direct.user.model;

import java.util.List;

public class CityModel {

    private Integer id;
    private String cityName;
    private List<AreaModel> districtList;

    public CityModel(String cityName, List<AreaModel> districtList) {
        this.cityName = cityName;
        this.districtList = districtList;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<AreaModel> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<AreaModel> districtList) {
        this.districtList = districtList;
    }


}
