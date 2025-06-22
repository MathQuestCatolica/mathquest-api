package com.mathquest.mathquest.feature.playeritem.service;

import com.mathquest.mathquest.feature.item.domain.Item;
import com.mathquest.mathquest.feature.item.dto.ItemDTO;
import com.mathquest.mathquest.feature.item.service.ItemService;
import com.mathquest.mathquest.feature.player.domain.Player;
import com.mathquest.mathquest.feature.player.dto.PlayerDTO;
import com.mathquest.mathquest.feature.player.service.PlayerService;
import com.mathquest.mathquest.feature.playeritem.domain.PlayerItem;
import com.mathquest.mathquest.feature.playeritem.dto.AssignPlayerItemDTO;
import com.mathquest.mathquest.feature.playeritem.dto.PlayerItemDTO;
import com.mathquest.mathquest.feature.playeritem.repository.PlayerItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class PlayerItemService {

    private final PlayerItemRepository playerItemRepository;
    private final PlayerService playerService;
    private final ItemService itemService;

    public PlayerItemService(PlayerItemRepository playerItemRepository, PlayerService playerService, ItemService itemService) {
        this.playerItemRepository = playerItemRepository;
        this.playerService = playerService;
        this.itemService = itemService;
    }

    public PlayerItemDTO getPlayerItemById(Long id) {
        PlayerItem playerItem = playerItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PlayerItem not found"));
        return convertToDTO(playerItem);
    }

    public List<PlayerItemDTO> getAllPlayerItems() {
        List<PlayerItem> playerItems = playerItemRepository.findAll();
        return playerItems.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public PlayerItemDTO editPlayerItem(PlayerItemDTO playerItemDTO, Long id) {
        PlayerItem playerItem = playerItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PlayerItem not found"));

        BeanUtils.copyProperties(playerItemDTO, playerItem, "id");
        playerItemRepository.save(playerItem);

        return convertToDTO(playerItem);
    }

    public boolean deletePlayerItem(Long id) {
        if (!playerItemRepository.existsById(id)) {
            throw new RuntimeException("PlayerItem not found with id: " + id);
        }
        playerItemRepository.deleteById(id);
        return true;
    }

    public PlayerItem createPlayerItem(PlayerItemDTO playerItemDTO) {
        PlayerItem playerItem = convertToEntity(playerItemDTO);
        return playerItemRepository.save(playerItem);
    }

    public PlayerItemDTO assignItemToPlayer(AssignPlayerItemDTO assignPlayerItemDTO) {
        PlayerDTO playerDTO = playerService.getPlayerById(assignPlayerItemDTO.getPlayerId());
        ItemDTO itemDTO = itemService.getItemById(assignPlayerItemDTO.getItemId());
        PlayerItemDTO playerItemDTO = new PlayerItemDTO();
        playerItemDTO.setPlayer(playerDTO);
        playerItemDTO.setItem(itemDTO);

        return convertToDTO(createPlayerItem(playerItemDTO));
    }

    private PlayerItemDTO convertToDTO(PlayerItem playerItem) {
        PlayerItemDTO dto = new PlayerItemDTO();
        dto.setId(playerItem.getId());

        dto.setPlayer(PlayerDTO.fromEntity(playerItem.getPlayer()));
        dto.setItem(ItemDTO.fromEntity(playerItem.getItem()));

        return dto;
    }

    private PlayerItem convertToEntity(PlayerItemDTO dto) {
        PlayerItem playerItem = new PlayerItem();
        playerItem.setPlayer(playerService.getPlayerByIdOrThrow(dto.getPlayer().getId()));
        playerItem.setItem(itemService.getItemByIdOrThrow(dto.getItem().getId()));
        return playerItem;
    }

    public void savePlayerItem(PlayerItem playerItem) {
        playerItemRepository.save(playerItem);
    }

    public List<PlayerItemDTO> getPlayerItemsByPlayerId(Long playerId) {
        // 1. Busca as relações do jogador
        List<PlayerItem> playerItems = playerItemRepository.findByPlayerId(playerId);

        // 2. Converte para DTO; se não houver itens, devolve lista vazia
        return playerItems.stream()
                .map(this::convertToDTO)   // usa o mesmo mapper que já existe no serviço
                .toList();
    }
}
