package com.sagara.momnkids.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class KidRequest implements Serializable {
    private String name;
    private String birthDate;
}
