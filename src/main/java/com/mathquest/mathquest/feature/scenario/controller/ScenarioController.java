package com.mathquest.mathquest.feature.scenario.controller;

import com.mathquest.mathquest.feature.item.dto.ItemDTO;
import com.mathquest.mathquest.feature.item.service.ItemService;
import com.mathquest.mathquest.feature.scenario.dto.ScenarioDTO;
import com.mathquest.mathquest.feature.scenario.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/scenario")
public class ScenarioController {
    @Autowired
    private ScenarioService scenarioService;

    @GetMapping
    public List<ScenarioDTO> getAllScenarios() {
        return scenarioService.getAllScenarios();
    }

    @GetMapping("/{id}")
    public ScenarioDTO getScenarioById(@PathVariable Long id) {
        return scenarioService.getScenarioById(id);
    }

    @PostMapping
    public ScenarioDTO createScenario(@RequestBody ScenarioDTO scenarioDTO) { return scenarioService.createScenario(scenarioDTO);}

    @PutMapping("/{id}")
    public ScenarioDTO updateScenario(@PathVariable Long id, @RequestBody ScenarioDTO scenarioDTO) {
        return scenarioService.updateScenario(id, scenarioDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteScenario(@PathVariable Long id) {
        scenarioService.deleteScenario(id);
    }
}
