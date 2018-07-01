package com.noventapp.direct.user.model;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

public class ContactUs {

    @Json(name = "id")
    private Integer id;
    @Json(name = "contactNumber")
    private String contactNumber;
    @Json(name = "countryid")
    private Integer countryid;
    @Json(name = "countryNameEn")
    private String countryNameEn;
    @Json(name = "countryNameAr")
    private String countryNameAr;
    @Json(name = "email")
    private String email;


    public String getBaseCountryName() {
        return LocalHelper.isLanguageEn() ? countryNameEn : countryNameAr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getCountryid() {
        return countryid;
    }

    public void setCountryid(Integer countryid) {
        this.countryid = countryid;
    }

    public String getCountryNameEn() {
        return countryNameEn;
    }

    public void setCountryNameEn(String countryNameEn) {
        this.countryNameEn = countryNameEn;
    }

    public String getCountryNameAr() {
        return countryNameAr;
    }

    public void setCountryNameAr(String countryNameAr) {
        this.countryNameAr = countryNameAr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
