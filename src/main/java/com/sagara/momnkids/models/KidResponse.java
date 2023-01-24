package com.sagara.momnkids.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sagara.momnkids.entity.Kid;

import lombok.Data;

@Data
public class KidResponse implements Serializable{
    private String id;
    private String name;
    private LocalDate birthDate;
    private LocalDateTime updated_at;

    public KidResponse(Kid kid){
        this.id = kid.getId();
        this.name = kid.getName();
        this.birthDate = kid.getBirthDate();
        this.updated_at = kid.getUpdated_at();
    }
}
