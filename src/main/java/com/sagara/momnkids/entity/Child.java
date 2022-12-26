package com.sagara.momnkids.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Child {
    @Id
    private String id;
    @JoinColumn
    @ManyToOne
    @JsonIgnore
    private User user;
    private BigDecimal age;
    private BigDecimal length;
    private BigDecimal weight;
    private String illustration;
    private String description;
}
