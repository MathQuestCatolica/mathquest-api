package com.mathquest.mathquest.feature.scenario.repository;

import com.mathquest.mathquest.feature.scenario.domain.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
}
