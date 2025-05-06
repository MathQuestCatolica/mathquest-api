package com.mathquest.mathquest.feature.scenario.domain;

import com.mathquest.mathquest.feature.player.domain.Player;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_scenario_progress")
@Data
public class ScenarioProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "scenario_id", nullable = false)
    private Scenario scenario;

    @Column(name = "total_time")
    private int totalTime;

}
