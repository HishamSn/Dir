package com.noventapp.direct.user.model;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

import java.util.List;

public class ClientInfoModel {

    @Json(name = "id")
    private Integer id;
    @Json(name = "serviceNumber")
    private String serviceNumber;
    @Json(name = "coverImage")
    private String coverImage;
    @Json(name = "clientNameAr")
    private String clientNameAr;
    @Json(name = "clientNameEn")
    private String clientNameEn;
    @Json(name = "clientSloganEn")
    private String clientSloganEn;
    @Json(name = "clientSloganAr")
    private String clientSloganAr;
    @Json(name = "hasDevlivery")
    private Boolean hasDevlivery;
    @Json(name = "hasBooking")
    private Boolean hasBooking;
    @Json(name = "hasSelfPickup")
    private Boolean hasSelfPickup;
    @Json(name = "overWholeRating")
    private Integer overWholeRating;
    @Json(name = "numberOfRatings")
    private Integer numberOfRatings;
    @Json(name = "numberOfViews")
    private Integer numberOfViews;
    @Json(name = "checkInNumber")
    private Integer checkInNumber;
    @Json(name = "amenities")
    private List<AmenitiesModel> amenities = null;
    @Json(name = "longtitude")
    private Double longtitude;
    @Json(name = "latitude")
    private Double latitude;
    @Json(name = "open")
    private Boolean open;
    @Json(name = "clientDescriptionEn")
    private String clientDescriptionEn;
    @Json(name = "clientDescriptionAr")
    private String clientDescriptionAr;
    @Json(name = "favored")
    private Boolean favored;
    private String baseDescription;
    private String clientBaseName;
    private String clientBaseSloganName;

    public String getBaseDescription() {
        return LocalHelper.isLanguageEn() ? clientDescriptionEn : clientDescriptionAr;
    }

    public String getClientBaseSloganName() {
        return LocalHelper.isLanguageEn() ? clientSloganEn : clientSloganAr;
    }

    public String getClientBaseName() {
        return LocalHelper.isLanguageEn() ? clientNameEn : clientNameAr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
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

    public String getClientSloganEn() {
        return clientSloganEn;
    }

    public void setClientSloganEn(String clientSloganEn) {
        this.clientSloganEn = clientSloganEn;
    }

    public String getClientSloganAr() {
        return clientSloganAr;
    }

    public void setClientSloganAr(String clientSloganAr) {
        this.clientSloganAr = clientSloganAr;
    }

    public Boolean getHasDevlivery() {
        return hasDevlivery;
    }

    public void setHasDevlivery(Boolean hasDevlivery) {
        this.hasDevlivery = hasDevlivery;
    }

    public Boolean getHasBooking() {
        return hasBooking;
    }

    public void setHasBooking(Boolean hasBooking) {
        this.hasBooking = hasBooking;
    }

    public Boolean getHasSelfPickup() {
        return hasSelfPickup;
    }

    public void setHasSelfPickup(Boolean hasSelfPickup) {
        this.hasSelfPickup = hasSelfPickup;
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

    public Integer getNumberOfViews() {
        return numberOfViews;
    }

    public void setNumberOfViews(Integer numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public Integer getCheckInNumber() {
        return checkInNumber;
    }

    public void setCheckInNumber(Integer checkInNumber) {
        this.checkInNumber = checkInNumber;
    }

    public List<AmenitiesModel> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<AmenitiesModel> amenities) {
        this.amenities = amenities;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getClientDescriptionEn() {
        return clientDescriptionEn;
    }

    public void setClientDescriptionEn(String clientDescriptionEn) {
        this.clientDescriptionEn = clientDescriptionEn;
    }

    public String getClientDescriptionAr() {
        return clientDescriptionAr;
    }

    public void setClientDescriptionAr(String clientDescriptionAr) {
        this.clientDescriptionAr = clientDescriptionAr;
    }

    public Boolean getFavored() {
        return favored;
    }

    public void setFavored(Boolean favored) {
        this.favored = favored;
    }
}
