package com.sagara.momnkids.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {
    private String username;
    private String email;
    private String roles;
    private String type = "Bearer";
    private String token;
    private String refreshToken;

    public JwtResponse(
            String username,
            String email,
            String roles,
            String accessToken,
            String refreshToken) {
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.token = accessToken;
        this.refreshToken = refreshToken;
    }
}