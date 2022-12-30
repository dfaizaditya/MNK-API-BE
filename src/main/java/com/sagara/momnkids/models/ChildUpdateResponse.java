package com.sagara.momnkids.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sagara.momnkids.entity.ChildUpdate;

import lombok.Data;

@Data
public class ChildUpdateResponse implements Serializable{
    private String id;
    private BigDecimal age;
    private BigDecimal length;
    private BigDecimal weight;
    private LocalDateTime updated_at;

    public ChildUpdateResponse(ChildUpdate childUpdate){
        this.id = childUpdate.getId();
        this.age = childUpdate.getAge();
        this.length = childUpdate.getLength();
        this.weight = childUpdate.getWeight();
        this.updated_at = childUpdate.getUpdated_at();
    }
}
