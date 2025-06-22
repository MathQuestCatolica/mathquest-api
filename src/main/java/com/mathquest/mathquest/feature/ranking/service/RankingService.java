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

    public List<Player> getTop10Players() {
        return playerRepository.findTop10Player();
    }

    public List<Player> getTop100Players() {
        return playerRepository.findTop100Player();
    }

    public Long getPlayerRanking(Long playerId) {
        return playerRepository.findRankByPlayerId(playerId);
    }

}
