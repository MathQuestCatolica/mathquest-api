package com.mathquest.mathquest.feature.item.domain;

import com.mathquest.mathquest.feature.item.enums.TypeItemEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Convert(converter = TypeItemEnum.class)
    private TypeItemEnum type;
}
