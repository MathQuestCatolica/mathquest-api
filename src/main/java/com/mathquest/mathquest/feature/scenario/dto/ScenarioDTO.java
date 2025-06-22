package com.mathquest.mathquest.feature.scenario.dto;

import com.mathquest.mathquest.feature.scenario.domain.Scenario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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


    public static ScenarioDTO build(Scenario entity) {
        if (entity == null) return null;

        return ScenarioDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .mode(entity.getMode())
                .route(entity.getRoute())
                .build();
    }

    public Scenario toEntity() {
        Scenario scenario = new Scenario();
        scenario.setId(this.id);
        scenario.setName(this.name);
        scenario.setMode(this.mode);
        scenario.setRoute(this.route);
        return scenario;
    }

}
