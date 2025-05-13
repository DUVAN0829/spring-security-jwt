package com.security.app.controller.dto;

import jakarta.validation.constraints.Size;

import java.util.List;

public class AuthCreateRoleRequest {

    //vars
    @Size(max = 3, message = "The user cannot have more than 3 roles")
    private List<String> roleListName;

    //Constructor
    public AuthCreateRoleRequest(List<String> roleListName) {
        this.roleListName = roleListName;
    }

    //Getters and Setters
    public List<String> getRoleListName() {
        return roleListName;
    }

    public void setRoleListName(List<String> roleListName) {
        this.roleListName = roleListName;
    }

}
