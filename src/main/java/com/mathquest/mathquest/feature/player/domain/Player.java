package com.mathquest.mathquest.feature.player.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_player")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "xp", nullable = false)
    private Integer xp;
}
