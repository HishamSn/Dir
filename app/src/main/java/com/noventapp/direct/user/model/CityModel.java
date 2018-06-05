package com.noventapp.direct.user.model;

import java.util.List;

public class CityModel {

    private Integer id;
    private String cityName;
    private List<DistrictModel> districtList;

    public CityModel(String cityName, List<DistrictModel> districtList) {
        this.cityName = cityName;
        this.districtList = districtList;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<DistrictModel> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<DistrictModel> districtList) {
        this.districtList = districtList;
    }


}
