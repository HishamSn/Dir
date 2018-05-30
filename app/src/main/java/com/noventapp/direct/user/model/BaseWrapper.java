package com.noventapp.direct.user.model;

import com.squareup.moshi.Json;

/*
 * Created by Hisham Snaimeh on 5/6/2018.
 */

public class BaseWrapper {

    @Json(name = "code")
    private int code;
    @Json(name = "status")
    private String status;
    @Json(name = "message")
    private String message;
    @Json(name = "last")
    private Boolean last;
    @Json(name = "totalPages")
    private Integer totalPages;
    @Json(name = "totalElements")
    private Integer totalElements;
    @Json(name = "number")
    private Integer number;
    @Json(name = "sort")
    private String sort;
    @Json(name = "size")
    private Integer size;
    @Json(name = "first")
    private Boolean first;

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
