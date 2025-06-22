package com.mathquest.mathquest.feature.rewards.domain;

import com.mathquest.mathquest.feature.item.domain.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_reward")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "max_xp", nullable = false)
    private long maxXp;

    @Column(name = "min_xp", nullable = false)
    private long minXp;

    @OneToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

}
