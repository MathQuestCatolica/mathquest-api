package com.mathquest.mathquest.feature.challenges.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name = "tb_challenges")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Challenges {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String description;

    private Date time;
}
