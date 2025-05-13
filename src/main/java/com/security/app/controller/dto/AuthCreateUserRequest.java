package com.security.app.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public class AuthCreateUserRequest {

    //vars
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @Valid
    private AuthCreateRoleRequest authCreateRoleRequest;

    //Constructors
    public AuthCreateUserRequest(String username, String password, AuthCreateRoleRequest authCreateRoleRequest) {
        this.username = username;
        this.password = password;
        this.authCreateRoleRequest = authCreateRoleRequest;
    }

    //Getters and Setters
    public @NotEmpty String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty String username) {
        this.username = username;
    }

    public @NotEmpty String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public @Valid AuthCreateRoleRequest getAuthCreateRoleRequest() {
        return authCreateRoleRequest;
    }

    public void setAuthCreateRoleRequest(@Valid AuthCreateRoleRequest authCreateRoleRequest) {
        this.authCreateRoleRequest = authCreateRoleRequest;
    }

}
