package com.noventapp.direct.user.model;

import com.squareup.moshi.Json;

public class TokenModel {
    @Json(name = "accessToken")
    private String accessToken;
    @Json(name = "tokenType")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

}
