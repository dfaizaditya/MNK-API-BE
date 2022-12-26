package com.sagara.momnkids.models;

import java.io.Serializable;
import java.math.BigDecimal;

import com.sagara.momnkids.entity.Child;

import lombok.Data;

@Data
public class ChildResponse implements Serializable{
    private String id;
    private BigDecimal age;
    private BigDecimal length;
    private BigDecimal weight;
    private String illustration;
    private String description;

    public ChildResponse(Child child){
        this.id = child.getId();
        this.age = child.getAge();
        this.length = child.getLength();
        this.weight = child.getWeight();
        this.illustration = child.getIllustration();
        this.description = child.getDescription();
    }
}
