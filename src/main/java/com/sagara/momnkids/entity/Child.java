package com.sagara.momnkids.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Child {
    @Id
    private String id;
    @JoinColumn
    @OneToOne(mappedBy = "child")
    @JsonIgnore
    private Pregnancy pregnancy;
    private BigDecimal age;
    private BigDecimal length;
    private BigDecimal weight;
    private String illustration;
    private String description;
}
