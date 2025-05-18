package com.mathquest.mathquest.feature.challenges.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengesDTO {
    private long id;

    private String title;

    private String description;

    private Date time;
}
