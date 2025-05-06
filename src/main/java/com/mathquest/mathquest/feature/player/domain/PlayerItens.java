package com.mathquest.mathquest.feature.player.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_player_itens")
@Data
public class PlayerItens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Itens item;
}

