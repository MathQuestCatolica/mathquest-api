package com.mathquest.mathquest.feature.player.repository;

import com.mathquest.mathquest.feature.player.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findByUsername(String username);

    @Query(value = """
        SELECT p.*
        FROM (
            SELECT  p.*,
                    COUNT(pi.id) AS item_count
            FROM    tb_player p
            LEFT JOIN tb_player_item pi ON pi.player_id = p.id
            GROUP BY p.id
        ) AS p
        ORDER BY p.xp DESC,
                 p.level DESC,
                 p.item_count DESC
        LIMIT 10
        """, nativeQuery = true)
    List<Player> findTop10Player();

    @Query(value = """
        SELECT p.*
        FROM (
            SELECT  p.*,
                    COUNT(pi.id) AS item_count
            FROM    tb_player p
            LEFT JOIN tb_player_item pi ON pi.player_id = p.id
            GROUP BY p.id
        ) AS p
        ORDER BY p.xp DESC,
                 p.level DESC,
                 p.item_count DESC
        LIMIT 100
        """, nativeQuery = true)
    List<Player> findTop100Player();

    @Query(value = """
        SELECT ranked.rank
        FROM (
            SELECT  p.id,
                    RANK() OVER (ORDER BY p.xp DESC,
                                          p.level DESC,
                                          p.item_count DESC) AS rank
            FROM (
                SELECT  p.id,
                        p.xp,
                        p.level,
                        COUNT(pi.id) AS item_count
                FROM    tb_player p
                LEFT JOIN tb_player_item pi ON pi.player_id = p.id
                GROUP BY p.id
            ) AS p
        ) AS ranked
        WHERE ranked.id = :playerId
        """, nativeQuery = true)
    Long findRankByPlayerId(@Param("playerId") Long playerId);
}
