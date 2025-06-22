package com.mathquest.mathquest.feature.rewards.repository;

import com.mathquest.mathquest.feature.rewards.domain.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<Reward, Long> {

    
}
