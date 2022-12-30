package com.sagara.momnkids.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagara.momnkids.entity.ChildUpdate;

public interface ChildUpdateRepository extends JpaRepository<ChildUpdate, String>  {
    
    List<ChildUpdate> findByPregnancyId(String pregnancyId);

}
