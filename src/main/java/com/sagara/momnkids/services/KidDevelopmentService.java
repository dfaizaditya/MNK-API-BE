package com.sagara.momnkids.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagara.momnkids.entity.Kid;
import com.sagara.momnkids.entity.KidDevelopment;
import com.sagara.momnkids.entity.Pregnancy;
import com.sagara.momnkids.exception.ResourceNotFoundException;
import com.sagara.momnkids.models.KidDevelopmentRequest;
import com.sagara.momnkids.models.KidDevelopmentResponse;
import com.sagara.momnkids.repository.KidDevelopmentRepository;

@Service 
public class KidDevelopmentService {

    @Autowired
    private KidDevelopmentRepository kidDevelopmentRepository;


    public KidDevelopment findById(String id) {
        return kidDevelopmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kehamilan dengan id " + id + " tidak ditemukan"));
    }

    public List<KidDevelopment> findAll(String kidId) {
        return kidDevelopmentRepository.findByKidId(kidId);
    }

    public KidDevelopmentResponse create(Kid kid, KidDevelopmentRequest request) {
        KidDevelopment kidDevelopment = new KidDevelopment();
        kidDevelopment.setId(UUID.randomUUID().toString());

        kidDevelopment.setAge(request.getAge());
        kidDevelopment.setHeight(request.getHeight());
        kidDevelopment.setWeight(request.getWeight());
        kidDevelopment.setHeadDiameter(request.getHeadDiameter());
        kidDevelopment.setBmi(request.getBmi());
        kidDevelopment.setIllustration(request.getIllustration());
        kidDevelopment.setDescription(request.getDescription());
        kidDevelopment.setKid(kid);

        kid.setKidDevelopment(kidDevelopment);

        KidDevelopment saved = kidDevelopmentRepository.save(kidDevelopment);
        KidDevelopmentResponse kidDevelopmentResponse = new KidDevelopmentResponse(saved);
        return kidDevelopmentResponse;
    }

    public KidDevelopmentResponse edit(KidDevelopment kidDevelopment, KidDevelopmentRequest request) {
        kidDevelopment.setAge(request.getAge());
        kidDevelopment.setHeight(request.getHeight());
        kidDevelopment.setWeight(request.getWeight());
        kidDevelopment.setHeadDiameter(request.getHeadDiameter());
        kidDevelopment.setBmi(request.getBmi());
        kidDevelopment.setIllustration(request.getIllustration());
        kidDevelopment.setDescription(request.getDescription());
        
        KidDevelopment saved = kidDevelopmentRepository.save(kidDevelopment);
        KidDevelopmentResponse kidDevelopmentResponse = new KidDevelopmentResponse(saved);
        return kidDevelopmentResponse;
    }

    public void deleteById(String id) {
        kidDevelopmentRepository.deleteById(id);
    }
    
}
