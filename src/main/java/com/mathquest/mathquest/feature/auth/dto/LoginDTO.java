package com.mathquest.mathquest.feature.auth.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDTO() {
    }
}
