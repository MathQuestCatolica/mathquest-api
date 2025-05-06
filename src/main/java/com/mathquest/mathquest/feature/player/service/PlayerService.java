package com.mathquest.mathquest.feature.player.service;

import com.mathquest.mathquest.feature.player.domain.Player;
import com.mathquest.mathquest.feature.player.dto.PlayerDTO;
import com.mathquest.mathquest.feature.player.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player createPlayer(PlayerDTO playerDTO) {
        Player player = Player.builder()
                .username(playerDTO.getUsername())
                .password(playerDTO.getPassword())
                .level(playerDTO.getLevel())
                .xp(playerDTO.getXp())
                .build();

        return playerRepository.save(player);
    }
}
