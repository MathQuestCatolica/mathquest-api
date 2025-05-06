package com.mathquest.mathquest.feature.player.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PlayerDTO {
    private Long id;

    private String username;

    private String password;

    private Integer level;

    private Integer xp;
}
