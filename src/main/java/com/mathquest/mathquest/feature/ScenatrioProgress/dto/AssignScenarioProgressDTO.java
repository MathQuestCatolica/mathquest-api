package com.mathquest.mathquest.feature.ScenatrioProgress.dto;

import lombok.Data;

@Data
public class AssignScenarioProgressDTO {
    private Long playerId;
    private Long scenarioId;
    private Integer totalTime;
}
