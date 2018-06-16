package com.noventapp.direct.user.model;

import com.auth0.android.jwt.JWT;

import java.util.Date;

public class LoginModel {


    JWT jwtToken;
    private Date exp;
    private Date iat;
    private String sub;


    public LoginModel(String jwtToken) {
        this.jwtToken = new JWT(jwtToken);
        sub = this.jwtToken.getSubject();
        exp = this.jwtToken.getExpiresAt();
        iat = this.jwtToken.getIssuedAt();

    }


    public Date getExp() {
        return exp;
    }

    public Date getIat() {
        return iat;
    }

    public String getSub() {
        return sub;
    }


}
