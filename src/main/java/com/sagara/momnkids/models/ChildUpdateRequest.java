package com.sagara.momnkids.models;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class ChildUpdateRequest implements Serializable {
    private BigDecimal age;
    private BigDecimal length;
    private BigDecimal weight;
}
