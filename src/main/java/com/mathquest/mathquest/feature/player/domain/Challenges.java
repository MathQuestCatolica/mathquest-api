package com.mathquest.mathquest.feature.player.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "tb_challenges")
public class Challenges {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String description;

    private Date time;
}
