package com.mathquest.mathquest.feature.auth.controller;

import com.mathquest.mathquest.feature.auth.dto.CreateAccountDTO;
import com.mathquest.mathquest.feature.auth.dto.LoginDTO;
import com.mathquest.mathquest.feature.auth.service.AuthService;
import com.mathquest.mathquest.feature.player.dto.PlayerDTO;
import com.mathquest.mathquest.shared.dto.ResponseDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<Object>> register(@RequestBody CreateAccountDTO createAccountDTO) {
        return ResponseEntity.ok(ResponseDTO.builder()
                .data(authService.createAccount(createAccountDTO))
                .statusMessage(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(200)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<Object>> login(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO);
        return ResponseEntity.ok(ResponseDTO.builder()
                .data(token)
                .statusMessage(HttpStatus.OK.getReasonPhrase())
                .statusCode(200)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
