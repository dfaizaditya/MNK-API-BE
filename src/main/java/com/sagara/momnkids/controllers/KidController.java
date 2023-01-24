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

import com.sagara.momnkids.entity.Kid;
import com.sagara.momnkids.models.KidRequest;
import com.sagara.momnkids.models.KidResponse;
import com.sagara.momnkids.response.ResponseHandler;
import com.sagara.momnkids.security.services.UserDetailsImpl;
import com.sagara.momnkids.services.KidService;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class KidController {

    @Autowired
    private KidService kidService;

    @PostMapping("/kids")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> create(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody KidRequest request) {
        try {
            KidResponse response = kidService.create(user.getUsername(), request);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, response);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @GetMapping("/kids")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> findAll(@AuthenticationPrincipal UserDetailsImpl user) {
        try {
            List<Kid> response = kidService.findAll(user.getUsername());
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, response);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
