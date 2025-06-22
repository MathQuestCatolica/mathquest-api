package com.mathquest.mathquest.feature.rewards.service;

import com.mathquest.mathquest.feature.item.domain.Item;
import com.mathquest.mathquest.feature.item.dto.ItemDTO;
import com.mathquest.mathquest.feature.item.repository.ItemRepository;
import com.mathquest.mathquest.feature.item.service.ItemService;
import com.mathquest.mathquest.feature.player.domain.Player;
import com.mathquest.mathquest.feature.player.dto.PlayerDTO;
import com.mathquest.mathquest.feature.player.repository.PlayerRepository;
import com.mathquest.mathquest.feature.player.service.PlayerService;
import com.mathquest.mathquest.feature.playeritem.domain.PlayerItem;
import com.mathquest.mathquest.feature.playeritem.repository.PlayerItemRepository;
import com.mathquest.mathquest.feature.playeritem.service.PlayerItemService;
import com.mathquest.mathquest.feature.rewards.domain.Reward;
import com.mathquest.mathquest.feature.rewards.dto.RewardDTO;
import com.mathquest.mathquest.feature.rewards.repository.RewardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardService {
    private final RewardRepository rewardRepository;
    private final ItemService itemService;
    private final PlayerRepository playerRepository;
    private final PlayerItemRepository playerItemRepository;

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
        Item item = itemService.getItemByIdOrThrow(rewardDTO.getItemId());
        rewardRepository.save(RewardDTO.toEntity(rewardDTO, item));
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

    @Transactional
    public void recalculateRewards(Long playerId) {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        if (!player.getPlayerItems().isEmpty()) {
            playerItemRepository.deleteByPlayerId(playerId);
            player.getPlayerItems().clear();
        }

        List<Reward> rewards = rewardRepository.findRewardsForXp(player.getXp());

        for (Reward reward : rewards) {
            PlayerItem link = new PlayerItem();
            link.setPlayer(player);
            link.setItem(reward.getItem());

            playerItemRepository.save(link);
            player.getPlayerItems().add(link);
        }
    }

}
