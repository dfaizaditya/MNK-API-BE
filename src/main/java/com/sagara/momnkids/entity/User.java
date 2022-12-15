package com.sagara.momnkids.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    private String id;
    @JsonIgnore
    private String password;
    private String name;
    @JsonIgnore
    private String adress;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String hp;
    @JsonIgnore
    private String roles;
    @JsonIgnore
    private Boolean isActive;

    public User(String username) {
        this.id = username;
    }

}
