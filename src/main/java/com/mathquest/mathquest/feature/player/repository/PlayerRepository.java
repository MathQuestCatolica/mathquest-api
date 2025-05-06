package com.mathquest.mathquest.feature.player.repository;

import com.mathquest.mathquest.feature.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
