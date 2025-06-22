package com.mathquest.mathquest.feature.item.dto;

import com.mathquest.mathquest.feature.item.domain.Item;
import com.mathquest.mathquest.feature.item.enums.TypeItemEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long id;
    private String name;
    private TypeItemEnum type;

    public static ItemDTO fromEntity(Item item) {
        if (item == null) return null;

        return ItemDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .type(item.getType())
                .build();
    }

    public Item toEntity() {
        Item entity = new Item();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setType(this.type);
        return entity;
    }
}
