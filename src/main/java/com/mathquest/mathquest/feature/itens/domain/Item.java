package com.mathquest.mathquest.feature.itens.domain;

import com.mathquest.mathquest.feature.itens.enums.TypeItemEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    @Convert(converter = TypeItemEnum.class)
    private TypeItemEnum tipo;
}
