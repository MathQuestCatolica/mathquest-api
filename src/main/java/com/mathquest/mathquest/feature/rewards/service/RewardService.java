package com.mathquest.mathquest.feature.rewards.service;

import com.mathquest.mathquest.feature.item.domain.Item;
import com.mathquest.mathquest.feature.item.dto.ItemDTO;
import com.mathquest.mathquest.feature.item.repository.ItemRepository;
import com.mathquest.mathquest.feature.item.service.ItemService;
import com.mathquest.mathquest.feature.player.domain.Player;
import com.mathquest.mathquest.feature.player.dto.PlayerDTO;
import com.mathquest.mathquest.feature.rewards.domain.Reward;
import com.mathquest.mathquest.feature.rewards.dto.RewardDTO;
import com.mathquest.mathquest.feature.rewards.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {
    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private ItemService itemService;

    public List<RewardDTO> getAllRewards() {
        return rewardRepository.findAll().stream()
                .map(RewardDTO::fromEntity)
                .toList();
    }

    public RewardDTO getRewardById(Long id) {
        return rewardRepository.findById(id)
                .map(RewardDTO::fromEntity)
                .orElse(null);
    }

    public void createReward(RewardDTO rewardDTO) {
        rewardRepository.save(RewardDTO.toEntity(rewardDTO, null));
    }

    public void deleteReward(Long id) {
        rewardRepository.deleteById(id);
    }

    public RewardDTO editReward(Long id, RewardDTO rewardDTO) {
        Reward reward = rewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reward not found with id: " + id));

        try {
            Item item = itemService.getItemByIdOrThrow(rewardDTO.getItemId());
            reward.setItem(item);
        } catch (RuntimeException e) {
            return null;
        }

        reward.setName(rewardDTO.getName());
        reward.setMaxXp(rewardDTO.getMaxXp());
        reward.setMinXp(rewardDTO.getMinXp());

        rewardRepository.save(reward);

        return RewardDTO.fromEntity(reward);
    }
}
