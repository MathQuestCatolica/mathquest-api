package com.mathquest.mathquest.feature.ScenatrioProgress.dto;

import com.mathquest.mathquest.feature.ScenatrioProgress.domain.ScenarioProgress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioProgressDTO {

    private Long id;
    private Long playerId;
    private Long scenarioId;
    private Integer totalTime;


    public static ScenarioProgressDTO fromEntity(ScenarioProgress progress) {
        return ScenarioProgressDTO.builder()
                .id(progress.getId())
                .playerId(progress.getPlayer().getId())
                .scenarioId(progress.getScenario().getId())
                .totalTime(progress.getTotalTime())
                .build();
    }

    public static ScenarioProgress toEntity(ScenarioProgressDTO dto) {
        ScenarioProgress progress = new ScenarioProgress();
        progress.setId(dto.getId());
        progress.setTotalTime(dto.getTotalTime());
        return progress;
    }
}
