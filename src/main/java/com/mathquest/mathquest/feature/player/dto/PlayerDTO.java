package com.mathquest.mathquest.feature.player.dto;

import com.mathquest.mathquest.feature.player.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    private Long id;
    private String username;
    private String password;
    private Integer level;
    private Integer xp;

    public static PlayerDTO fromEntity(Player player) {
        if (player == null) return null;

        return PlayerDTO.builder()
                .id(player.getId())
                .username(player.getUsername())
                .password(player.getPassword())
                .level(player.getLevel())
                .xp(player.getXp())
                .build();
    }

    public Player toEntity() {
        Player p = new Player();
        p.setId(this.id);
        p.setUsername(this.username);
        p.setPassword(this.password);
        p.setLevel(this.level);
        p.setXp(this.xp);
        return p;
    }
}
