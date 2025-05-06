package com.mathquest.mathquest.feature.player.enums;

public enum TipoItemEnum {
    RELIQUIA(0),
    ARMA(1),
    ARMADURA(2),
    CURA(3);

    private final int code;

    TipoItemEnum(int codigo) {
        this.code = codigo;
    }

    public int getType() {
        return code;
    }

    public static TipoItemEnum getTypeItemByCode(int code) {
        for (TipoItemEnum tipo : values()) {
            if (tipo.getType() == code) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido para TipoItemEnum: " + code);
    }
}