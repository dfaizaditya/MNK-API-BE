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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sagara.momnkids.entity.Child;
import com.sagara.momnkids.entity.Pregnancy;
import com.sagara.momnkids.models.ChildRequest;
import com.sagara.momnkids.models.ChildResponse;
import com.sagara.momnkids.models.ImageRequest;
import com.sagara.momnkids.response.ResponseHandler;
import com.sagara.momnkids.security.services.UserDetailsImpl;
import com.sagara.momnkids.services.ChildService;
import com.sagara.momnkids.services.ChildUpdateService;
import com.sagara.momnkids.services.PregnancyService;
import com.sagara.momnkids.services.StorageService;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class ChildController {

    @Autowired
    private ChildService childService;

    @Autowired
    private ChildUpdateService childUpdateService;

    @Autowired
    private PregnancyService pregnancyService;

    @Autowired
    private StorageService storageService;

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

    @PutMapping("/pregnancies/{pregnancyId}/child")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> update(
            @PathVariable String pregnancyId,
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody ChildRequest request) {
        try {
            Child child = childService.findAll(pregnancyId).get(0);
            ChildResponse response = childService.edit(child, request);
            childUpdateService.create(pregnancyService.findById(pregnancyId), request);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, response);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @PutMapping("/pregnancies/{pregnancyId}/child/image")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> updateImage(
            @PathVariable String pregnancyId,
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestParam("image")MultipartFile image) {
        try {
            String response = storageService.uploadImage(image);
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
