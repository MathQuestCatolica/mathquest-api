package com.mathquest.mathquest.feature.ranking.controller;

import com.mathquest.mathquest.feature.player.domain.Player;
import com.mathquest.mathquest.feature.ranking.service.RankingService;
import com.mathquest.mathquest.shared.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ranking")
public class RankingController {
    @Autowired
    private RankingService rankingService;

    @GetMapping("/top10")
    public ResponseEntity<ResponseDTO<List<Player>>> getTop10Players() {
        return ResponseEntity.ok(
            ResponseDTO.<List<Player>>builder()
                .data(rankingService.getTop10Players())
                .statusMessage("Top 10 players retrieved successfully.")
                .statusCode(200)
                .build()
        );
    }

    @GetMapping("top100")
    public ResponseEntity<ResponseDTO<List<Player>>> getTop100Players() {
        return ResponseEntity.ok(
                ResponseDTO.<List<Player>>builder()
                        .data(rankingService.getTop10Players())
                        .statusMessage("Top 100 players retrieved successfully.")
                        .statusCode(200)
                        .build()
        );
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<ResponseDTO<List<String>>> getPlayerRanking(@PathVariable Long playerId) {
        Long rank = rankingService.getPlayerRanking(playerId);
        return ResponseEntity.ok(
                ResponseDTO.<List<String>>builder()
                        .data(List.of("Player ID: " + playerId, "Rank: " + rank))
                        .statusMessage("Player ranking retrieved successfully.")
                        .statusCode(200)
                        .build()
        );
    }

}
