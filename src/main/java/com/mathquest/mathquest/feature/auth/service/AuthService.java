package com.mathquest.mathquest.feature.auth.service;

import com.mathquest.mathquest.config.security.utils.JwtUtil;
import com.mathquest.mathquest.feature.auth.dto.CreateAccountDTO;
import com.mathquest.mathquest.feature.auth.dto.LoginDTO;
import com.mathquest.mathquest.feature.player.domain.Player;
import com.mathquest.mathquest.feature.player.dto.PlayerDTO;
import com.mathquest.mathquest.feature.player.service.PlayerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PlayerService playerService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(PlayerService playerService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.playerService = playerService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public Player createAccount(CreateAccountDTO createAccountDTO) {
        PlayerDTO playerDTO = PlayerDTO.builder()
                .xp(0)
                .level(0)
                .password(passwordEncoder.encode(createAccountDTO.getPassword()))
                .username(createAccountDTO.getUsername())
                .build();

        return playerService.createPlayer(playerDTO);
    }

    public String login(LoginDTO loginDTO) {
        Player player = playerService.getPlayerByUsername(loginDTO.getUsername());
        if (player != null && passwordEncoder.matches(loginDTO.getPassword(), player.getPassword())) {
            return jwtUtil.generateToken(player.getUsername());
        }

        throw new RuntimeException("Invalid username or password");
    }
}
