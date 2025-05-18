package com.mathquest.mathquest.feature.playeritem.dto;

import com.mathquest.mathquest.feature.item.dto.ItemDTO;
import com.mathquest.mathquest.feature.player.dto.PlayerDTO;
import lombok.Data;

@Data
public class PlayerItemDTO {

    private Long id;
    private PlayerDTO player;
    private ItemDTO item;
}
