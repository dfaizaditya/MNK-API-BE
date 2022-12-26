package com.sagara.momnkids.models;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class ChildRequest implements Serializable {
    private BigDecimal age;
    private BigDecimal length;
    private BigDecimal weight;
    private String illustration;
    private String description;
}
