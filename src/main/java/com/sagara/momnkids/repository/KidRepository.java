package com.sagara.momnkids.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagara.momnkids.entity.Kid;

public interface KidRepository extends JpaRepository<Kid, String>  {
    
    List<Kid> findByUserId(String userId);

}
