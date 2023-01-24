package com.sagara.momnkids.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagara.momnkids.entity.Child;
import com.sagara.momnkids.entity.Pregnancy;
import com.sagara.momnkids.exception.ResourceNotFoundException;
import com.sagara.momnkids.models.ChildRequest;
import com.sagara.momnkids.models.ChildResponse;
import com.sagara.momnkids.repository.ChildRepository;

@Service 
public class ChildService {

    @Autowired
    private ChildRepository childRepository;


    public Child findById(String id) {
        return childRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kehamilan dengan id " + id + " tidak ditemukan"));
    }

    public List<Child> findAll(String pregnancyId) {
        return childRepository.findByPregnancyId(pregnancyId);
    }

    public ChildResponse create(Pregnancy pregnancy, ChildRequest request) {
        Child child = new Child();
        child.setId(UUID.randomUUID().toString());

        child.setAge(request.getAge());
        child.setLength(request.getLength());
        child.setWeight(request.getWeight());
        child.setDescription(request.getDescription());
        child.setPregnancy(pregnancy);

        pregnancy.setChild(child);

        Child saved = childRepository.save(child);
        ChildResponse childResponse = new ChildResponse(saved);
        return childResponse;
    }

    public ChildResponse edit(Child child, ChildRequest request) {
        child.setAge(request.getAge());
        child.setLength(request.getLength());
        child.setWeight(request.getWeight());
        child.setDescription(request.getDescription());
        
        Child saved = childRepository.save(child);
        ChildResponse childResponse = new ChildResponse(saved);
        return childResponse;
    }

    public void deleteById(String id) {
        childRepository.deleteById(id);
    }
    
}
