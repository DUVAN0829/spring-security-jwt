package com.security.app.controller;

import com.security.app.controller.dto.AuthCreateUser;
import com.security.app.controller.dto.AuthLoginRequest;
import com.security.app.controller.dto.AuthResponse;
import com.security.app.services.UserDetailServicesImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    //vars
    @Autowired
    private UserDetailServicesImpl services;

    //Methods
    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest authLoginRequest) {
        return ResponseEntity.ok(services.LoginUser(authLoginRequest));
    }

    @PostMapping("/sing-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUser authCreateUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(services.createUser(authCreateUser));
    }

}
