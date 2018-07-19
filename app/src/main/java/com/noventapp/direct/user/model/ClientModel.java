package com.noventapp.direct.user.model;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

public class ClientModel {


    @Json(name = "branchId")
    private Integer branchId;
    @Json(name = "clientId")
    private Integer clientId;
    @Json(name = "clientNameAr")
    private String clientNameAr;
    @Json(name = "clientNameEn")
    private String clientNameEn;
    @Json(name = "clientSloganAr")
    private String clientSloganAr;
    @Json(name = "clientSloganEn")
    private String clientSloganEn;
    @Json(name = "overWholeRating")
    private Integer overWholeRating;
    @Json(name = "numberOfRatings")
    private Integer numberOfRatings;
    @Json(name = "longitude")
    private Double longitude;
    @Json(name = "latitude")
    private Double latitude;
    @Json(name = "coverPhoto")
    private String coverPhoto;
    @Json(name = "delivery")
    private Boolean delivery;
    @Json(name = "booking")
    private Boolean booking;
    @Json(name = "selfPickup")
    private Boolean selfPickup;
    @Json(name = "favored")
    private Boolean favored;
    @Json(name = "amenities")
    private String clientBaseName;
    private String clientBaseSloganName;

    public String getClientBaseName() {
        return LocalHelper.isLanguageEn() ? clientNameEn : clientNameAr;
    }

    public String getClientBaseSloganName() {
        return LocalHelper.isLanguageEn() ? clientSloganEn : clientSloganAr;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getClientNameAr() {
        return clientNameAr;
    }

    public void setClientNameAr(String clientNameAr) {
        this.clientNameAr = clientNameAr;
    }

    public String getClientNameEn() {
        return clientNameEn;
    }

    public void setClientNameEn(String clientNameEn) {
        this.clientNameEn = clientNameEn;
    }

    public Object getClientSloganAr() {
        return clientSloganAr;
    }

    public void setClientSloganAr(String clientSloganAr) {
        this.clientSloganAr = clientSloganAr;
    }

    public Object getClientSloganEn() {
        return clientSloganEn;
    }

    public void setClientSloganEn(String clientSloganEn) {
        this.clientSloganEn = clientSloganEn;
    }

    public Integer getOverWholeRating() {
        return overWholeRating;
    }

    public void setOverWholeRating(Integer overWholeRating) {
        this.overWholeRating = overWholeRating;
    }

    public Integer getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(Integer numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String  getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public Boolean getBooking() {
        return booking;
    }

    public void setBooking(Boolean booking) {
        this.booking = booking;
    }

    public Boolean getSelfPickup() {
        return selfPickup;
    }

    public void setSelfPickup(Boolean selfPickup) {
        this.selfPickup = selfPickup;
    }

    public Boolean getFavored() {
        return favored;
    }

    public void setFavored(Boolean favored) {
        this.favored = favored;
    }
}
