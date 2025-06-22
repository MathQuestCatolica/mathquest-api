package com.mathquest.mathquest.feature.ScenatrioProgress.service;

import com.mathquest.mathquest.feature.ScenatrioProgress.domain.ScenarioProgress;
import com.mathquest.mathquest.feature.ScenatrioProgress.dto.AssignScenarioProgressDTO;
import com.mathquest.mathquest.feature.ScenatrioProgress.dto.ScenarioProgressDTO;
import com.mathquest.mathquest.feature.ScenatrioProgress.repository.ScenarioProgressRepository;
import com.mathquest.mathquest.feature.player.domain.Player;
import com.mathquest.mathquest.feature.player.service.PlayerService;
import com.mathquest.mathquest.feature.rewards.service.RewardService;
import com.mathquest.mathquest.feature.scenario.domain.Scenario;
import com.mathquest.mathquest.feature.scenario.service.ScenarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioProgressService {
    @Autowired
    private final ScenarioProgressRepository scenarioProgressRepository;

    @Autowired
    private final PlayerService playerService;

    @Autowired
    private final ScenarioService scenarioService;

    @Autowired
    private final RewardService rewardService;

    public ScenarioProgressService(ScenarioProgressRepository scenarioProgressRepository,
                                   PlayerService playerService,
                                   ScenarioService scenarioService, RewardService rewardService) {
        this.scenarioProgressRepository = scenarioProgressRepository;
        this.playerService = playerService;
        this.scenarioService = scenarioService;
        this.rewardService = rewardService;
    }

    public ScenarioProgressDTO getScenarioProgressById(Long id) {
        ScenarioProgress progress = scenarioProgressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ScenarioProgress not found"));
        return convertToDTO(progress);
    }

    public List<ScenarioProgressDTO> getAllScenarioProgress() {
        return scenarioProgressRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public ScenarioProgress createScenarioProgress(ScenarioProgressDTO dto) {
        ScenarioProgress progress = convertToEntity(dto);
        return scenarioProgressRepository.save(progress);
    }

    public ScenarioProgressDTO editScenarioProgress(Long id, ScenarioProgressDTO dto) {
        ScenarioProgress progress = scenarioProgressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ScenarioProgress not found"));

        progress.setTotalTime(dto.getTotalTime());

        if (dto.getPlayerId() != null) {
            progress.setPlayer(playerService.getPlayerByIdOrThrow(dto.getPlayerId()));
        }
        if (dto.getScenarioId() != null) {
           progress.setScenario(scenarioService.getScenarioEntityByID(dto.getScenarioId()));
        }

        scenarioProgressRepository.save(progress);
        return convertToDTO(progress);
    }

    public boolean deleteScenarioProgress(Long id) {
        if (!scenarioProgressRepository.existsById(id)) {
            throw new RuntimeException("ScenarioProgress not found with id: " + id);
        }
        scenarioProgressRepository.deleteById(id);
        return true;
    }

    private ScenarioProgressDTO convertToDTO(ScenarioProgress progress) {
        ScenarioProgressDTO dto = new ScenarioProgressDTO();
        BeanUtils.copyProperties(progress, dto);
        dto.setScenarioId(progress.getScenario().getId());
        dto.setPlayerId(progress.getPlayer().getId());
        return dto;
    }

    private ScenarioProgress convertToEntity(ScenarioProgressDTO dto) {
        ScenarioProgress progress = new ScenarioProgress();
        if (dto.getPlayerId() != null) {
            progress.setPlayer(playerService.getPlayerByIdOrThrow(dto.getPlayerId()));
        }
        if (dto.getScenarioId() != null) {
            progress.setScenario(scenarioService.getScenarioEntityByID(dto.getScenarioId()));
        }
        progress.setTotalTime(dto.getTotalTime());
        return progress;
    }

    public ScenarioProgressDTO assignScenarioToPlayer(AssignScenarioProgressDTO dto) {
        Player player = playerService.getPlayerByIdOrThrow(dto.getPlayerId());
        Scenario scenario = scenarioService.getScenarioEntityByID(dto.getScenarioId());

        ScenarioProgress progress = new ScenarioProgress();
        progress.setPlayer(player);
        progress.setScenario(scenario);
        progress.setTotalTime(dto.getTotalTime());

        progress = scenarioProgressRepository.save(progress);
        return ScenarioProgressDTO.fromEntity(progress);
    }

    @Transactional
    public ScenarioProgressDTO passScenario(AssignScenarioProgressDTO dto) {

        Player player = playerService.getPlayerByIdOrThrow(dto.getPlayerId());
        Scenario newScenario = scenarioService.getScenarioEntityByID(dto.getScenarioId() + 1);

        if (newScenario == null) {
            throw new RuntimeException("Next scenario not found");
        }

        player.setLevel(player.getLevel() + 1);
        player.setXp(player.getXp() + 100);
        rewardService.recalculateRewards(player.getId());
        playerService.savePlayer(player);

        ScenarioProgress progress = new ScenarioProgress();
        progress.setPlayer(player);
        progress.setScenario(newScenario);
        progress.setTotalTime(dto.getTotalTime());

        progress = scenarioProgressRepository.save(progress);

        return ScenarioProgressDTO.fromEntity(progress);
    }
}
