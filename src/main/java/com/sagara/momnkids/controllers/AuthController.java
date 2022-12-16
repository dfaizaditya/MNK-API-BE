package com.sagara.momnkids.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagara.momnkids.entity.User;
import com.sagara.momnkids.models.JwtResponse;
import com.sagara.momnkids.models.LoginRequest;
import com.sagara.momnkids.models.RefreshTokenRequest;
import com.sagara.momnkids.models.RegisterRequest;
import com.sagara.momnkids.response.ResponseHandler;
import com.sagara.momnkids.security.jwt.JwtUtils;
import com.sagara.momnkids.security.services.UserDetailsImpl;
import com.sagara.momnkids.security.services.UserDetailsServiceImpl;
import com.sagara.momnkids.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            String token = jwtUtils.generateJwtToken(authentication);
            String refreshToken = jwtUtils.generateRefresJwtToken(authentication);
            UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();

            JwtResponse result = new JwtResponse(principal.getUsername(), principal.getEmail(), principal.getRoles(),
                    token, refreshToken);
            return ResponseHandler.generateResponse("Login Success", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {

        try {
            User myUser = new User();
            myUser.setId(request.getUsername());
            myUser.setEmail(request.getEmail());
            myUser.setPassword(passwordEncoder.encode(request.getPassword()));
            myUser.setName(request.getName());
            myUser.setRoles(request.getRoles());
            User created = userService.create(myUser);
            return ResponseHandler.generateResponse("Register Success", HttpStatus.OK, created);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        String token = request.getRefreshToken();
        boolean valid = jwtUtils.validateJwtToken(token);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String username = jwtUtils.getUserNameFromJwtToken(token);
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetailsImpl, null,
                userDetailsImpl.getAuthorities());
        String newToken = jwtUtils.generateJwtToken(authentication);
        String refreshToken = jwtUtils.generateRefresJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(username, userDetailsImpl.getEmail(), userDetailsImpl.getRoles(),
                newToken, refreshToken));
    }
}
