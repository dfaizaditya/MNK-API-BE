package com.sagara.momnkids.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sagara.momnkids.entity.Pregnancy;
import com.sagara.momnkids.enums.PregnancyStatus;

import lombok.Data;

@Data
public class PregnancyResponse implements Serializable{
    private String id;
    private PregnancyStatus pregnancyStatus;
    private LocalDate hpht;
    private LocalDate hpl;
    private LocalDateTime updated_at;

    public PregnancyResponse(Pregnancy pregnancy){
        this.id = pregnancy.getId();
        this.pregnancyStatus = pregnancy.getPregnancyStatus();
        this.hpht =  pregnancy.getHpht();
        this.hpl = pregnancy.getHpl();
        this.updated_at = pregnancy.getUpdated_at();
    }
}
