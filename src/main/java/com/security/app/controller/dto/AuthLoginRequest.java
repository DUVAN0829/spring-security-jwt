package com.security.app.controller.dto;

import jakarta.validation.constraints.NotEmpty;

public class AuthLoginRequest {

    //vars
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    //Constructor
    public AuthLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
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

}
