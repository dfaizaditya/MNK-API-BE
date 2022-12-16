package com.sagara.momnkids.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class RegisterRequest implements Serializable {

    private String username;
    private String password;
    private String email;
    private String name;
    private String roles;

}
