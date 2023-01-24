package com.sagara.momnkids.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class KidDevelopment {
    @Id
    private String id;
    @JoinColumn
    @OneToOne(mappedBy = "kidDevelopment")
    @JsonIgnore
    private Kid kid;
    private BigDecimal age;
    private BigDecimal height;
    private BigDecimal weight;
    private BigDecimal headDiameter;
    private BigDecimal bmi;
    private String illustration;
    private String description;
}
