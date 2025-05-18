package com.mathquest.mathquest.feature.player.controller;

import com.mathquest.mathquest.feature.player.domain.Player;
import com.mathquest.mathquest.feature.player.dto.PlayerDTO;
import com.mathquest.mathquest.feature.player.service.PlayerService;
import com.mathquest.mathquest.shared.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<Player>>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();

        ResponseDTO<List<Player>> response = new ResponseDTO<>(
                200,
                "Players retrieved successfully",
                LocalDateTime.now(),
                players
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<PlayerDTO>> getPlayerById(@PathVariable("id") long playerID) {
        PlayerDTO player = playerService.getPlayerById(playerID);

        

        ResponseDTO<PlayerDTO> response = new ResponseDTO<>(
                200,
                "Player retrieved successfully",
                LocalDateTime.now(),
                player
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<Player>> editPlayer(@PathVariable Long id, @RequestBody PlayerDTO playerDTO) {
        Player updated = playerService.editPlayer(id, playerDTO);

        ResponseDTO<Player> response = new ResponseDTO<>(
                200,
                "Player updated successfully",
                LocalDateTime.now(),
                updated
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO<Void>> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);

        ResponseDTO<Void> response = new ResponseDTO<>(
                200,
                "Player deleted successfully",
                LocalDateTime.now(),
                null
        );

        return ResponseEntity.ok(response);
    }



}
