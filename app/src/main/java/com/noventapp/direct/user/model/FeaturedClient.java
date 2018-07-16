package com.noventapp.direct.user.model;

import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

public class FeaturedClient {

    @Json(name = "id")
    private Integer id;
    @Json(name = "companyName")
    private String companyNameEn;
    @Json(name = "companyNameAR")
    private String companyNameAR;
    @Json(name = "sloganEN")
    private String sloganEN;
    @Json(name = "sloganAR")
    private String sloganAR;
    @Json(name = "coverImage")
    private String coverImage;

    private String baseCompanyName;

    public String getBaseCompanyName() {
        return LocalHelper.isLanguageEn() ? companyNameEn : companyNameAR;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyNameEn() {
        return companyNameEn;
    }

    public void setCompanyName(String companyNameEn) {
        this.companyNameEn = companyNameEn;
    }

    public String getCompanyNameAR() {
        return companyNameAR;
    }

    public void setCompanyNameAR(String companyNameAR) {
        this.companyNameAR = companyNameAR;
    }

    public String getSloganEN() {
        return sloganEN;
    }

    public void setSloganEN(String sloganEN) {
        this.sloganEN = sloganEN;
    }

    public String getSloganAR() {
        return sloganAR;
    }

    public void setSloganAR(String sloganAR) {
        this.sloganAR = sloganAR;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
