package com.sagara.momnkids.models;

import java.io.Serializable;
import java.math.BigDecimal;

import com.sagara.momnkids.entity.KidDevelopment;

import lombok.Data;

@Data
public class KidDevelopmentResponse implements Serializable{
    private String id;
    private BigDecimal age;
    private BigDecimal height;
    private BigDecimal weight;
    private BigDecimal headDiameter;
    private BigDecimal bmi;
    private String illustration;
    private String description;

    public KidDevelopmentResponse(KidDevelopment kidDevelopment){
        this.id = kidDevelopment.getId();
        this.age = kidDevelopment.getAge();
        this.height = kidDevelopment.getHeight();
        this.weight = kidDevelopment.getWeight();
        this.headDiameter = kidDevelopment.getHeadDiameter();
        this.bmi = kidDevelopment.getBmi();
        this.illustration = kidDevelopment.getIllustration();
        this.description = kidDevelopment.getDescription();
    }
}
