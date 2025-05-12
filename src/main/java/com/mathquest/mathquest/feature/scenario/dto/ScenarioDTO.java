package com.mathquest.mathquest.feature.scenario.dto;

import lombok.Data;

@Data
public class ScenarioDTO {
    private Long id;
    private String name;
    private String mode;
    private String route;

    public ScenarioDTO(Long id, String name, String mode, String route) {
        this.id = id;
        this.name = name;
        this.mode = mode;
        this.route = route;
    }

    public ScenarioDTO() {
    }
}
