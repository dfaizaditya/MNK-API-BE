package com.sagara.momnkids.models;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class KidDevelopmentRequest implements Serializable {
    private BigDecimal age;
    private BigDecimal height;
    private BigDecimal weight;
    private BigDecimal headDiameter;
    private BigDecimal bmi;
    private String illustration;
    private String description;
}
