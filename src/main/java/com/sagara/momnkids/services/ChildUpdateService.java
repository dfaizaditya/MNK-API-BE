package com.sagara.momnkids.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagara.momnkids.entity.ChildUpdate;
import com.sagara.momnkids.entity.Pregnancy;
import com.sagara.momnkids.exception.ResourceNotFoundException;
import com.sagara.momnkids.models.ChildRequest;
import com.sagara.momnkids.repository.ChildUpdateRepository;

@Service 
public class ChildUpdateService {

    @Autowired
    private ChildUpdateRepository childUpdateRepository;


    public ChildUpdate findById(String id) {
        return childUpdateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kehamilan dengan id " + id + " tidak ditemukan"));
    }

    public List<ChildUpdate> findAll(String pregnancyId) {
        return childUpdateRepository.findByPregnancyId(pregnancyId);
    }

    public void create(Pregnancy pregnancy, ChildRequest request) {
        ChildUpdate childUpdate = new ChildUpdate();
        childUpdate.setId(UUID.randomUUID().toString());

        childUpdate.setAge(request.getAge());
        childUpdate.setLength(request.getLength());
        childUpdate.setWeight(request.getWeight());
        childUpdate.setUpdated_at(LocalDateTime.now());
        childUpdate.setPregnancy(pregnancy);

        childUpdateRepository.save(childUpdate);
    }

    public ChildUpdate edit(ChildUpdate childUpdate, ChildRequest request) {
        return childUpdateRepository.save(childUpdate);
    }

    public void deleteById(String id) {
        childUpdateRepository.deleteById(id);
    }
    
}
