package com.noventapp.direct.user.model;

import com.squareup.moshi.Json;

import java.util.List;

public class ClientModel {

    @Json(name = "id")
    private Integer id;
    @Json(name = "companyName")
    private String companyName;
    @Json(name = "companyNameAR")
    private Object companyNameAR;
    @Json(name = "phoneNumber")
    private String phoneNumber;
    @Json(name = "sloganEN")
    private Object sloganEN;
    @Json(name = "sloganAR")
    private Object sloganAR;
    @Json(name = "logo")
    private Object logo;
    @Json(name = "serviceNumber")
    private Object serviceNumber;
    @Json(name = "coverImage")
    private Object coverImage;
    @Json(name = "filtersId")
    private List<Object> filtersId = null;
    @Json(name = "topSelling")
    private Boolean topSelling;
    @Json(name = "delivery")
    private Boolean delivery;
    @Json(name = "booking")
    private Boolean booking;
    @Json(name = "selfPickup")
    private Boolean selfPickup;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Object getCompanyNameAR() {
        return companyNameAR;
    }

    public void setCompanyNameAR(Object companyNameAR) {
        this.companyNameAR = companyNameAR;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getSloganEN() {
        return sloganEN;
    }

    public void setSloganEN(Object sloganEN) {
        this.sloganEN = sloganEN;
    }

    public Object getSloganAR() {
        return sloganAR;
    }

    public void setSloganAR(Object sloganAR) {
        this.sloganAR = sloganAR;
    }

    public Object getLogo() {
        return logo;
    }

    public void setLogo(Object logo) {
        this.logo = logo;
    }

    public Object getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(Object serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public Object getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Object coverImage) {
        this.coverImage = coverImage;
    }

    public List<Object> getFiltersId() {
        return filtersId;
    }

    public void setFiltersId(List<Object> filtersId) {
        this.filtersId = filtersId;
    }

    public Boolean getTopSelling() {
        return topSelling;
    }

    public void setTopSelling(Boolean topSelling) {
        this.topSelling = topSelling;
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
}
