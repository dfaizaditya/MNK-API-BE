package com.sagara.momnkids.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagara.momnkids.entity.Child;
import com.sagara.momnkids.entity.Pregnancy;
import com.sagara.momnkids.entity.User;
import com.sagara.momnkids.enums.PregnancyStatus;
import com.sagara.momnkids.exception.ResourceNotFoundException;
import com.sagara.momnkids.models.PregnancyRequest;
import com.sagara.momnkids.models.PregnancyResponse;
import com.sagara.momnkids.repository.PregnancyRepository;
import com.sagara.momnkids.utils.DateHelper;
import com.sagara.momnkids.utils.PregnancyCalc;

@Service 
public class PregnancyService {

    @Autowired
    private PregnancyRepository pregnancyRepository;

    public Pregnancy findById(String id) {
        return pregnancyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kehamilan dengan id " + id + " tidak ditemukan"));
    }

    public List<Pregnancy> findAll(String userId) {
        return pregnancyRepository.findByUserId(userId);
    }

    public PregnancyResponse create(String username, PregnancyRequest request) {
        Pregnancy pregnancy = new Pregnancy();
        pregnancy.setId(UUID.randomUUID().toString());
        pregnancy.setUser(new User(username));
        pregnancy.setPregnancyStatus(PregnancyStatus.DALAM_KANDUNGAN);
        pregnancy.setHpht(DateHelper.StringToDate(request.getHpht()));


        if(request.getHpl() != (null)){
            pregnancy.setHpl(DateHelper.StringToDate(request.getHpl()));
        }else{
            LocalDate newDate = PregnancyCalc.pregdue(DateHelper.StringToDate(request.getHpht()));
            pregnancy.setHpl(newDate);
        }
        
        pregnancy.setUpdated_at(LocalDateTime.now());

        Child child = new Child();
        child.setId(UUID.randomUUID().toString());
        child.setPregnancy(pregnancy);

        pregnancy.setChild(child);

        Pregnancy saved = pregnancyRepository.save(pregnancy);
        PregnancyResponse pregnancyResponse = new PregnancyResponse(saved);
        
        return pregnancyResponse;
    }

    public Pregnancy edit(Pregnancy pregnancy) {
        return pregnancyRepository.save(pregnancy);
    }

    public void deleteById(String id) {
        pregnancyRepository.deleteById(id);
    }
    
}
