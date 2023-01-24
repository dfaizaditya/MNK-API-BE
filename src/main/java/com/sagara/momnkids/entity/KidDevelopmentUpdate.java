package com.sagara.momnkids.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class KidDevelopmentUpdate {
    @Id
    private String id;
    @JoinColumn
    @ManyToOne
    @JsonIgnore
    private Kid kid;
    private BigDecimal age;
    private BigDecimal height;
    private BigDecimal weight;
    private BigDecimal headDiameter;
    private BigDecimal bmi;
    @Column(columnDefinition="TIMESTAMP")  
    @UpdateTimestamp
    private LocalDateTime updated_at;
}
