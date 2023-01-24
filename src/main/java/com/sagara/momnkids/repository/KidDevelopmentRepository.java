package com.sagara.momnkids.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagara.momnkids.entity.KidDevelopment;

public interface KidDevelopmentRepository extends JpaRepository<KidDevelopment, String>  {
    
    List<KidDevelopment> findByKidId(String kidId);

}
