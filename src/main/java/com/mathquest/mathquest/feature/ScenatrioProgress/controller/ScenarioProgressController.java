package com.mathquest.mathquest.feature.ScenatrioProgress.controller;


import com.mathquest.mathquest.feature.ScenatrioProgress.dto.AssignScenarioProgressDTO;
import com.mathquest.mathquest.feature.ScenatrioProgress.dto.ScenarioProgressDTO;
import com.mathquest.mathquest.feature.ScenatrioProgress.service.ScenarioProgressService;
import com.mathquest.mathquest.shared.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/scenario-progress")
public class ScenarioProgressController {

    @Autowired
    private ScenarioProgressService scenarioProgressService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<ScenarioProgressDTO>>> getAllScenarioProgress() {
        List<ScenarioProgressDTO> list = scenarioProgressService.getAllScenarioProgress();

        if (Objects.isNull(list) || list.isEmpty()) {
            return ResponseEntity.status(404).body(
                    new ResponseDTO<>(404, "No scenario progress found", LocalDateTime.now(), null)
            );
        }
        return ResponseEntity.ok(
                new ResponseDTO<>(200, "Scenario progress retrieved successfully", LocalDateTime.now(), list)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<ScenarioProgressDTO>> getScenarioProgressById(@PathVariable Long id) {
        ScenarioProgressDTO dto = scenarioProgressService.getScenarioProgressById(id);

        if (Objects.isNull(dto)) {
            return ResponseEntity.status(404).body(
                    new ResponseDTO<>(404, "Scenario progress not found", LocalDateTime.now(), null)
            );
        }
        return ResponseEntity.ok(
                new ResponseDTO<>(200, "Scenario progress retrieved successfully", LocalDateTime.now(), dto)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<ScenarioProgressDTO>> createScenarioProgress(@RequestBody ScenarioProgressDTO dto) {
        ScenarioProgressDTO created = ScenarioProgressDTO.fromEntity(
                scenarioProgressService.createScenarioProgress(dto)
        );
        return ResponseEntity.status(201).body(
                new ResponseDTO<>(201, "Scenario progress created successfully", LocalDateTime.now(), created)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<ScenarioProgressDTO>> editScenarioProgress(@PathVariable Long id,
                                                                                 @RequestBody ScenarioProgressDTO dto) {
        ScenarioProgressDTO updated = scenarioProgressService.editScenarioProgress(id, dto);

        if (Objects.isNull(updated)) {
            return ResponseEntity.status(404).body(
                    new ResponseDTO<>(404, "Scenario or player not found", LocalDateTime.now(), null)
            );
        }
        return ResponseEntity.ok(
                new ResponseDTO<>(200, "Scenario progress updated successfully", LocalDateTime.now(), updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteScenarioProgress(@PathVariable Long id) {
        try {
            scenarioProgressService.deleteScenarioProgress(id);
            return ResponseEntity.status(204).body(
                    new ResponseDTO<>(204, "Scenario progress deleted successfully", LocalDateTime.now(), null)
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    new ResponseDTO<>(404, e.getMessage(), LocalDateTime.now(), null)
            );
        }
    }

    @PostMapping("/assign")
    public ResponseEntity<ResponseDTO<ScenarioProgressDTO>> assignScenario(@RequestBody AssignScenarioProgressDTO dto) {
        ScenarioProgressDTO created = scenarioProgressService.assignScenarioToPlayer(dto);
        return ResponseEntity.status(201).body(
                new ResponseDTO<>(201, "Scenario progress registered", LocalDateTime.now(), created)
        );
    }

    @PostMapping("/pass")
    public ResponseEntity<ResponseDTO<ScenarioProgressDTO>> passScenario(
            @RequestBody AssignScenarioProgressDTO dto) {

        ScenarioProgressDTO finished = scenarioProgressService.passScenario(dto);

        return ResponseEntity.status(201).body(
                new ResponseDTO<>(201, "Scenario finished, rewards recalculated",
                        LocalDateTime.now(), finished));
    }
}
