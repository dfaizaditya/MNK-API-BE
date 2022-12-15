package com.sagara.momnkids.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagara.momnkids.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

    Boolean existsByEmail(String email);

}
