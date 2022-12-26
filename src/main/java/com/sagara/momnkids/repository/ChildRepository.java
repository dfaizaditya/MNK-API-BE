package com.sagara.momnkids.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagara.momnkids.entity.Child;

public interface ChildRepository extends JpaRepository<Child, String>  {
    
    List<Child> findByUserId(String userId);

}
