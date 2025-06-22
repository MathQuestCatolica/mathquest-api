package com.mathquest.mathquest.feature.rewards.dto;

package com.mathquest.mathquest.feature.rewards.api.dto;

import com.mathquest.mathquest.feature.item.domain.Item;
import com.mathquest.mathquest.feature.rewards.domain.Reward;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardDTO {

    private Long id;
    private String name;
    private Long maxXp;
    private Long minXp;
    private Long itemId;

    public static RewardDTO fromEntity(Reward reward) {
        return RewardDTO.builder()
                .id(reward.getId())
                .name(reward.getName())
                .maxXp(reward.getMaxXp())
                .minXp(reward.getMinXp())
                .itemId(reward.getItem().getId())
                .build();
    }

    public static Reward toEntity(RewardDTO dto, Item item) {
        return Reward.builder()
                .id(dto.getId())
                .name(dto.getName())
                .maxXp(dto.getMaxXp())
                .minXp(dto.getMinXp())
                .item(item)
                .build();
    }
}

