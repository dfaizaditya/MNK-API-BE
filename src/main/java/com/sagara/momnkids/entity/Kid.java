package com.sagara.momnkids.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
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
public class Kid {
    @Id
    private String id;
    @JoinColumn
    @ManyToOne
    @JsonIgnore
    private User user;
    private String name;
    @Column(columnDefinition="DATE") 
    private LocalDate birthDate;
    @Column(columnDefinition="TIMESTAMP")  
    @UpdateTimestamp
    private LocalDateTime updated_at;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private KidDevelopment kidDevelopment;
}
