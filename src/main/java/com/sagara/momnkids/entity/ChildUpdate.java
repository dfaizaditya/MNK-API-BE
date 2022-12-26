package com.sagara.momnkids.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class ChildUpdate {
    @Id
    private String id;
    @JoinColumn
    @ManyToOne
    @JsonIgnore
    private Pregnancy pregnancy;
    private BigDecimal age;
    private BigDecimal length;
    private BigDecimal weight;
    @Column(columnDefinition="TIMESTAMP")  
    @UpdateTimestamp
    private LocalDateTime updated_at;
}
