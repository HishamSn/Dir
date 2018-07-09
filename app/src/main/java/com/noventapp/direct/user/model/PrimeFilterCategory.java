package com.noventapp.direct.user.model;


import com.noventapp.direct.user.utils.LocalHelper;
import com.squareup.moshi.Json;

public class PrimeFilterCategory {
    @Json(name = "id")
    private Integer id;
    @Json(name = "filterAr")
    private String filterAr;
    @Json(name = "filterEn")
    private String filterEn;
    @Json(name = "filterCategoryId")
    private Integer filterCategoryId;
    @Json(name = "filterImage")
    private String filterImage;
    private String baseName;

    public String getBaseName() {
        return LocalHelper.isLanguageEn() ? filterEn : filterAr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilterAr() {
        return filterAr;
    }

    public void setFilterAr(String filterAr) {
        this.filterAr = filterAr;
    }

    public String getFilterEn() {
        return filterEn;
    }

    public void setFilterEn(String filterEn) {
        this.filterEn = filterEn;
    }

    public Integer getFilterCategoryId() {
        return filterCategoryId;
    }

    public void setFilterCategoryId(Integer filterCategoryId) {
        this.filterCategoryId = filterCategoryId;
    }

    public String getFilterImage() {
        return filterImage;
    }

    public void setFilterImage(String filterImage) {
        this.filterImage = filterImage;
    }


}
