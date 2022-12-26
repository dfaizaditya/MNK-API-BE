package com.sagara.momnkids.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagara.momnkids.entity.Pregnancy;
import com.sagara.momnkids.models.PregnancyRequest;
import com.sagara.momnkids.models.PregnancyResponse;
import com.sagara.momnkids.response.ResponseHandler;
import com.sagara.momnkids.security.services.UserDetailsImpl;
import com.sagara.momnkids.services.PregnancyService;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class PregnancyController {

    @Autowired
    private PregnancyService pregnancyService;

    @PostMapping("/pregnancies")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> create(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody PregnancyRequest request) {
        try {
            PregnancyResponse response = pregnancyService.create(user.getUsername(), request);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, response);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @GetMapping("/pregnancies")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> findAll(@AuthenticationPrincipal UserDetailsImpl user) {
        try {
            List<Pregnancy> response = pregnancyService.findAll(user.getUsername());
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, response);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
