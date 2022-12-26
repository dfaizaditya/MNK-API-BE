package com.sagara.momnkids.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagara.momnkids.entity.Pregnancy;

public interface PregnancyRepository extends JpaRepository<Pregnancy, String>  {
    
    List<Pregnancy> findByUserId(String userId);

}
