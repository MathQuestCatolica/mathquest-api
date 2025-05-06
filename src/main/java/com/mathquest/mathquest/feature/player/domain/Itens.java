package com.mathquest.mathquest.feature.player.domain;

import com.mathquest.mathquest.feature.player.enums.TipoItemEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_itens")
public class Itens {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    @Convert(converter = TipoItemEnum.class)
    private TipoItemEnum tipo;
}
