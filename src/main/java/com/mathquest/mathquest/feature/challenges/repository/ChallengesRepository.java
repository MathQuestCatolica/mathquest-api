package com.mathquest.mathquest.feature.challenges.repository;

import com.mathquest.mathquest.feature.challenges.domain.Challenges;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengesRepository extends JpaRepository<Challenges, Long> {

    public Challenges findByTitle(String title);
}
