package com.mathquest.mathquest.feature.player.service;

import com.mathquest.mathquest.feature.player.domain.Player;
import com.mathquest.mathquest.feature.player.dto.PlayerDTO;
import com.mathquest.mathquest.feature.player.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id).orElse(null);

        if (Objects.isNull(player)) return null;

        return PlayerDTO.builder()
                .id(player.getId())
                .username(player.getUsername())
                .password(player.getPassword())
                .level(player.getLevel())
                .xp(player.getXp())
                .build();
    }

    public Player getPlayerByIdOrThrow(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));
    }

    public void deletePlayer(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new RuntimeException("Player not found with id: " + id);
        }
        playerRepository.deleteById(id);
    }

    public Player editPlayer(Long id, PlayerDTO playerDTO) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));

        player.setUsername(playerDTO.getUsername());
        player.setPassword(playerDTO.getPassword());
        player.setLevel(playerDTO.getLevel());
        player.setXp(playerDTO.getXp());

        return playerRepository.save(player);
    }

    public Player getPlayerByUsername(String username) {
        return playerRepository.findByUsername(username);
    }

    public Player deadPlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found with id: " + id));

        Integer xpPlayer = player.getXp();

        if (xpPlayer == 0) return player;

        if (xpPlayer < 0) {
            player.setXp(0);
        } else {
            player.setXp(xpPlayer - 50);

            if (player.getXp() < 0) {
                player.setXp(0);
            }
        }
        playerRepository.save(player);
        return player;
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }
}
