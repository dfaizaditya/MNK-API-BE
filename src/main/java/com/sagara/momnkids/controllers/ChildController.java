package com.sagara.momnkids.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagara.momnkids.entity.Child;
import com.sagara.momnkids.models.ChildRequest;
import com.sagara.momnkids.models.ChildResponse;
import com.sagara.momnkids.response.ResponseHandler;
import com.sagara.momnkids.security.services.UserDetailsImpl;
import com.sagara.momnkids.services.ChildService;
import com.sagara.momnkids.services.PregnancyService;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class ChildController {

    @Autowired
    private ChildService childService;

    @Autowired
    private PregnancyService pregnancyService;

    @PostMapping("/pregnancies/{pregnancyId}/child")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> create(
            @PathVariable String pregnancyId,
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody ChildRequest request) {
        try {
            ChildResponse response = childService.create(pregnancyService.findById(pregnancyId), request);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, response);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @GetMapping("/pregnancies/{pregnancyId}/child")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> findAll(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable String pregnancyId) {
        try {
            List<Child> response = childService.findAll(pregnancyId);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, response.get(0));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
