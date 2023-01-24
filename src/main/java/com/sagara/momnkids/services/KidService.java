package com.sagara.momnkids.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagara.momnkids.entity.Child;
import com.sagara.momnkids.entity.Kid;
import com.sagara.momnkids.entity.KidDevelopment;
import com.sagara.momnkids.entity.User;
import com.sagara.momnkids.exception.ResourceNotFoundException;
import com.sagara.momnkids.models.KidRequest;
import com.sagara.momnkids.models.KidResponse;
import com.sagara.momnkids.repository.KidRepository;
import com.sagara.momnkids.utils.DateHelper;

@Service 
public class KidService {

    @Autowired
    private KidRepository kidRepository;

    public Kid findById(String id) {
        return kidRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kehamilan dengan id " + id + " tidak ditemukan"));
    }

    public List<Kid> findAll(String userId) {
        return kidRepository.findByUserId(userId);
    }

    public KidResponse create(String username, KidRequest request) {
        Kid kid = new Kid();
        kid.setId(UUID.randomUUID().toString());
        kid.setUser(new User(username));
        kid.setName(request.getName());
        kid.setBirthDate(DateHelper.StringToDate(request.getBirthDate()));
        kid.setUpdated_at(LocalDateTime.now());

        KidDevelopment kidDev = new KidDevelopment();
        kidDev.setId(UUID.randomUUID().toString());
        kidDev.setKid(kid);

        kid.setKidDevelopment(kidDev);

        Kid saved = kidRepository.save(kid);
        KidResponse kidResponse = new KidResponse(saved);
        
        return kidResponse;
    }

    public Kid edit(Kid kid) {
        return kidRepository.save(kid);
    }

    public void deleteById(String id) {
        kidRepository.deleteById(id);
    }
    
}
