package com.sagara.momnkids.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class PregnancyRequest implements Serializable {
    private String hpht;
    private String hpl;
}
