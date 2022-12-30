package com.sagara.momnkids.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagara.momnkids.entity.ChildUpdate;
import com.sagara.momnkids.response.ResponseHandler;
import com.sagara.momnkids.security.services.UserDetailsImpl;
import com.sagara.momnkids.services.ChildUpdateService;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class ChildUpdateController {

    @Autowired
    private ChildUpdateService childUpdateService;

    @GetMapping("/pregnancies/{pregnancyId}/child/update")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> findAll(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable String pregnancyId) {
        try {
            List<ChildUpdate> response = childUpdateService.findAll(pregnancyId);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, response);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

}
