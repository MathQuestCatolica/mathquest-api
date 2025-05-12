package com.mathquest.mathquest.feature.scenario.service;

import com.mathquest.mathquest.feature.scenario.domain.Scenario;
import com.mathquest.mathquest.feature.scenario.dto.ScenarioDTO;
import com.mathquest.mathquest.feature.scenario.repository.ScenarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ScenarioService {
    private final ScenarioRepository scenarioRepository;

    public ScenarioService(ScenarioRepository scenarioRepository) {
        this.scenarioRepository = scenarioRepository;
    }

    public List<ScenarioDTO> getAllScenarios() {
        return scenarioRepository.findAll().stream()
                .map(sceneario -> new ScenarioDTO(sceneario.getId(), sceneario.getName(), sceneario.getMode(), sceneario.getRoute()))
                .toList();
    }

    public ScenarioDTO getScenarioById(Long id) {
        return scenarioRepository.findById(id)
                .map(sceneario -> new ScenarioDTO(sceneario.getId(), sceneario.getName(), sceneario.getMode(), sceneario.getRoute()))
                .orElse(null);
    }

    public ScenarioDTO createScenario(ScenarioDTO scenarioDTO) {
        Scenario scenario = new Scenario();
        scenario.setName(scenarioDTO.getName());
        scenario.setMode(scenarioDTO.getMode());
        scenario.setRoute(scenarioDTO.getRoute());
        scenario = scenarioRepository.save(scenario);
        return new ScenarioDTO(scenario.getId(), scenario.getName(), scenario.getMode(), scenario.getRoute());
    }

    public ScenarioDTO updateScenario(Long id, ScenarioDTO scenarioDTO) {
        Scenario scenario = scenarioRepository.findById(id).orElse(null);
        if (scenario != null) {
            scenario.setName(Objects.nonNull(scenarioDTO.getName()) ? scenarioDTO.getName() : scenario.getName());
            scenario.setMode(Objects.nonNull(scenarioDTO.getMode()) ? scenarioDTO.getMode() : scenario.getMode());
            scenario = scenarioRepository.save(scenario);
            return new ScenarioDTO(scenario.getId(), scenario.getName(), scenario.getMode(), scenario.getRoute());
        }
        return null;
    }

    public void deleteScenario(Long id) {
        scenarioRepository.deleteById(id);
    }
}
