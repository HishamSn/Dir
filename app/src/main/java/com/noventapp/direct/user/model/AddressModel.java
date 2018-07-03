package com.noventapp.direct.user.model;

import com.squareup.moshi.Json;

public class AddressModel {
    @Json(name = "addressName")
    private String addressName;
    @Json(name = "buildingNumber")
    private String buildingNumber;
    @Json(name = "floorNumber")
    private String floorNumber;
    @Json(name = "apartmentNumber")
    private String apartmentNumber;
    @Json(name = "latitude")
    private Double latitude;
    @Json(name = "longtitude")
    private Double longtitude;
    @Json(name = "customerId")
    private Integer customerId;
    @Json(name = "id")
    private Integer id;

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
