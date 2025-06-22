package com.mathquest.mathquest.feature.ScenatrioProgress.repository;

import com.mathquest.mathquest.feature.ScenatrioProgress.domain.ScenarioProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioProgressRepository extends JpaRepository<ScenarioProgress, Long> {
}
