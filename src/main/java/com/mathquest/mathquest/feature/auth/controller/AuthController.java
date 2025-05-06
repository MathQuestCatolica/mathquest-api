package com.mathquest.mathquest.feature.auth.controller;

import com.mathquest.mathquest.feature.auth.dto.CreateAccountDTO;
import com.mathquest.mathquest.feature.auth.service.AuthService;
import com.mathquest.mathquest.feature.player.dto.PlayerDTO;
import com.mathquest.mathquest.shared.dto.ResponseDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseDTO<Object> login(@RequestBody CreateAccountDTO createAccountDTO) {
        return ResponseDTO.builder()
                .data(authService.createAccount(createAccountDTO))
                .statusMessage(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(200)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PostMapping("/register")
    public String register() {
        return "Registration successful!";
    }
}
