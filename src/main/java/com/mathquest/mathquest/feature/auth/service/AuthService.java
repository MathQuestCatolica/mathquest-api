package com.mathquest.mathquest.feature.auth.service;

import com.mathquest.mathquest.feature.auth.dto.CreateAccountDTO;
import com.mathquest.mathquest.feature.auth.dto.LoginDTO;
import com.mathquest.mathquest.feature.player.domain.Player;
import com.mathquest.mathquest.feature.player.dto.PlayerDTO;
import com.mathquest.mathquest.feature.player.service.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PlayerService playerService;

    public AuthService(PlayerService playerService) {
        this.playerService = playerService;
    }

    public Player createAccount(CreateAccountDTO createAccountDTO) {
        PlayerDTO playerDTO = PlayerDTO.builder()
                .xp(0)
                .level(0)
                .password(createAccountDTO.getPassword())
                .username(createAccountDTO.getUsername())
                .build();

        return playerService.createPlayer(playerDTO);
    }

    public void login(LoginDTO loginDTO) {
        Player player = playerService.getPlayerByUsername(loginDTO.getUsername());
        if (player != null && player.getPassword().equals(loginDTO.getPassword())) {
            return;
        }

        throw new RuntimeException("Invalid username or password");
    }
}
