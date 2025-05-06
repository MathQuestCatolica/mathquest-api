package com.mathquest.mathquest.feature.player.domain;

import com.mathquest.mathquest.feature.player.enums.TipoItemEnum;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    @Convert(converter = TipoItemEnum.class)
    private TipoItemEnum tipo;
}
