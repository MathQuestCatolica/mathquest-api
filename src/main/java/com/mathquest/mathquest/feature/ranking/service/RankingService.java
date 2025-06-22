package com.mathquest.mathquest.feature.ranking.service;

import com.mathquest.mathquest.feature.player.domain.Player;
import com.mathquest.mathquest.feature.player.repository.PlayerRepository;
import com.mathquest.mathquest.feature.player.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<String> getTop10Players() {
        List<Player> top10Player = playerRepository.findTop10Player();
        List<String> playerNames = top10Player.stream()
                .map(Player::getUsername)
                .toList();

        return playerNames;
    }

    public List<String> getTop100Players() {
        List<Player> top100Players = playerRepository.findTop100Player();
        List<String> playerNames = top100Players.stream()
                .map(Player::getUsername)
                .toList();
        return playerNames;
    }

    public Long getPlayerRanking(Long playerId) {
        return playerRepository.findRankByPlayerId(playerId);
    }

}
