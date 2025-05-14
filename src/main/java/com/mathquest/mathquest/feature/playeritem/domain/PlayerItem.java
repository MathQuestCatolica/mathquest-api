package com.mathquest.mathquest.feature.playeritem.domain;

import com.mathquest.mathquest.feature.item.domain.Item;
import com.mathquest.mathquest.feature.player.domain.Player;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_player_item")
@Data
public class PlayerItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;
}
