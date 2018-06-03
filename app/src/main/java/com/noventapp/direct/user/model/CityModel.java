package com.noventapp.direct.user.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;
import java.util.UUID;

public class CityModel implements ParentObject {

    private String cityName;
    private UUID id;
    private List<Object> districtList;

    public CityModel(String cityName) {
        this.cityName = cityName;
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public List<Object> getChildObjectList() {
        return districtList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        districtList = list;

    }
}
