package com.mathquest.mathquest.feature.playeritem.controller;

import com.mathquest.mathquest.feature.playeritem.domain.PlayerItem;
import com.mathquest.mathquest.feature.playeritem.dto.AssignPlayerItemDTO;
import com.mathquest.mathquest.feature.playeritem.dto.PlayerItemDTO;
import com.mathquest.mathquest.feature.playeritem.service.PlayerItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.RequestPath;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/player-item")
public class PlayerItemController {

    @Autowired
    PlayerItemService playerItemService;

    @GetMapping
    public List<PlayerItemDTO> getAllPlayerItems() {
        return playerItemService.getAllPlayerItems();
    }

    @GetMapping("/{id}")
    public PlayerItemDTO getPlayerItemById(@PathVariable Long id) {
        return playerItemService.getPlayerItemById(id);
    }

    @PutMapping("/{id}")
    public PlayerItemDTO editPlayerItem(@RequestBody PlayerItemDTO playerItemDTO, @PathVariable Long id) {
        return playerItemService.editPlayerItem(playerItemDTO, id);
    }

    @DeleteMapping("/{id}")
    public boolean deletePlayerItem(@PathVariable Long id) {
        return playerItemService.deletePlayerItem(id);
    }

    @PostMapping("/assign")
    public PlayerItemDTO assignItemToPlayer(@RequestBody AssignPlayerItemDTO assignPlayerItemDTO) {
        return playerItemService.assignItemToPlayer(assignPlayerItemDTO);
    }
}
