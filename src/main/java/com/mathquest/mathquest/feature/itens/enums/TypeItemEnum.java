package com.mathquest.mathquest.feature.itens.enums;

public enum TypeItemEnum {
    RELIQUIA(0),
    ARMA(1),
    ARMADURA(2),
    CURA(3);

    private final int code;

    TypeItemEnum(int codigo) {
        this.code = codigo;
    }

    public int getType() {
        return code;
    }

    public static TypeItemEnum getTypeItemByCode(int code) {
        for (TypeItemEnum tipo : values()) {
            if (tipo.getType() == code) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido para TipoItemEnum: " + code);
    }
}