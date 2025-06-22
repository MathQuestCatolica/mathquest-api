package com.mathquest.mathquest.feature.rewards.controller;

import com.mathquest.mathquest.feature.rewards.dto.RewardDTO;
import com.mathquest.mathquest.feature.rewards.service.RewardService;
import com.mathquest.mathquest.shared.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/reward")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<RewardDTO>>> getAllRewards() {
        List<RewardDTO> rewards = rewardService.getAllRewards();

        if (Objects.isNull(rewards) || rewards.isEmpty()) {
            return ResponseEntity.status(404).body(
                    new ResponseDTO<>(404, "No rewards found", LocalDateTime.now(), null)
            );
        }
        return ResponseEntity.ok(
                new ResponseDTO<>(200, "Rewards retrieved successfully", LocalDateTime.now(), rewards)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<RewardDTO>> getRewardById(@PathVariable Long id) {
        RewardDTO reward = rewardService.getRewardById(id);

        if (Objects.isNull(reward)) {
            return ResponseEntity.status(404).body(
                    new ResponseDTO<>(404, "Reward not found", LocalDateTime.now(), null)
            );
        }
        return ResponseEntity.ok(
                new ResponseDTO<>(200, "Reward retrieved successfully", LocalDateTime.now(), reward)
        );
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<RewardDTO>> createReward(@RequestBody RewardDTO rewardDTO) {
        rewardService.createReward(rewardDTO);

        return ResponseEntity.status(201).body(
                new ResponseDTO<>(201, "Reward created successfully", LocalDateTime.now(), rewardDTO)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<RewardDTO>> editReward(@PathVariable Long id,
                                                             @RequestBody RewardDTO rewardDTO) {
        RewardDTO updated = rewardService.editReward(id, rewardDTO);

        if (Objects.isNull(updated)) {
            return ResponseEntity.status(404).body(
                    new ResponseDTO<>(404, "Item not found for reward or reward does not exist",
                            LocalDateTime.now(), null)
            );
        }
        return ResponseEntity.ok(
                new ResponseDTO<>(200, "Reward updated successfully", LocalDateTime.now(), updated)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteReward(@PathVariable Long id) {
        rewardService.deleteReward(id);
        return ResponseEntity.status(204).body(
                new ResponseDTO<>(204, "Reward deleted successfully", LocalDateTime.now(), null)
        );
    }

    @PutMapping("/recalculate/{idPlayer}")
    public ResponseEntity<ResponseDTO<Void>> recalculateRewards(@PathVariable Long idPlayer) {
        rewardService.recalculateRewards(idPlayer);
        return ResponseEntity.ok(
                new ResponseDTO<>(200, "Rewards recalculated successfully", LocalDateTime.now(), null)
        );
    }
}
