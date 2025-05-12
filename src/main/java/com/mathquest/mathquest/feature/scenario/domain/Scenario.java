package com.mathquest.mathquest.feature.scenario.domain;

import com.mathquest.mathquest.feature.player.domain.Player;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "tb_scenarios")
@Data
@Entity
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String mode;

    private String route;
}
