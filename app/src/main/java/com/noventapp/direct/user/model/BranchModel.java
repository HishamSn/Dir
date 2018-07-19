package com.noventapp.direct.user.model;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

public class BranchModel {

    @Json(name = "id")
    private Integer id;
    @Json(name = "clientId")
    private Integer clientId;
    @Json(name = "name")
    private String name;
    @Json(name = "nameAR")
    private String nameAR;
    @Json(name = "latitude")
    private Double latitude;
    @Json(name = "longitude")
    private Double longitude;
    @Json(name = "phoneNumber")
    private String phoneNumber;
    @Json(name = "hasBooking")
    private Boolean hasBooking;
    private String baseBranchName;

    public String getBaseBranchName() {
        return LocalHelper.isLanguageEn() ? name : nameAR;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAR() {
        return nameAR;
    }

    public void setNameAR(String nameAR) {
        this.nameAR = nameAR;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getHasBooking() {
        return hasBooking;
    }

    public void setHasBooking(Boolean hasBooking) {
        this.hasBooking = hasBooking;
    }

}
