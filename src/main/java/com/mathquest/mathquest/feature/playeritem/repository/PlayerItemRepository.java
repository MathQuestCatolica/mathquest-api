package com.mathquest.mathquest.feature.playeritem.repository;

import com.mathquest.mathquest.feature.playeritem.domain.PlayerItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerItemRepository extends JpaRepository<PlayerItem, Long> {
}
