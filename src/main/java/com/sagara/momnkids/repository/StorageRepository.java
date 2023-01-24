package com.sagara.momnkids.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagara.momnkids.entity.ImageData;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {

    Optional<ImageData> findByName(String fileName);
}
