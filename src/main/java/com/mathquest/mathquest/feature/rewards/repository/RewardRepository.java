package com.mathquest.mathquest.feature.rewards.repository;

import com.mathquest.mathquest.feature.rewards.domain.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {

    @Query("""
           SELECT r
           FROM Reward r
           WHERE :xp BETWEEN r.minXp AND r.maxXp
           ORDER BY r.minXp
           """)
    List<Reward> findRewardsForXp(@Param("xp") long xp);

}
