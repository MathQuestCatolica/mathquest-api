package com.mathquest.mathquest.feature.playeritem.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class AssignPlayerItemDTO {
    @NonNull
    private Long playerId;
    @NonNull
    private Long itemId;
}
