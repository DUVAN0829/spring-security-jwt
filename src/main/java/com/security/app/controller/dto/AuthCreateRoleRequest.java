package com.security.app.controller.dto;

import com.security.app.entities.RoleEnum;
import jakarta.validation.constraints.Size;

import java.util.List;

public class AuthCreateRoleRequest {

    //vars
    @Size(max = 3, message = "The user cannot have more than 3 roles")
    private List<RoleEnum> roleListName;

    //Constructor
    public AuthCreateRoleRequest(List<RoleEnum> roleListName) {
        this.roleListName = roleListName;
    }

    //Getters and Setters
    public @Size(max = 3, message = "The user cannot have more than 3 roles") List<RoleEnum> getRoleListName() {
        return roleListName;
    }

    public void setRoleListName(@Size(max = 3, message = "The user cannot have more than 3 roles") List<RoleEnum> roleListName) {
        this.roleListName = roleListName;
    }
}
