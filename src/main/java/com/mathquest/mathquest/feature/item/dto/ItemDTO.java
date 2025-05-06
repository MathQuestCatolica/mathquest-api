package com.mathquest.mathquest.feature.item.dto;

import com.mathquest.mathquest.feature.item.enums.TypeItemEnum;
import lombok.Data;

@Data
public class ItemDTO {
    private Long id;
    private String name;
    private TypeItemEnum type;

    public ItemDTO(Long id, String name, TypeItemEnum type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public ItemDTO() {
    }
}
