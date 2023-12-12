package com.eduardokp.assembleia.model.enums;

/**
 * Enumeração com as possibilidades de valores para votos
 */
public enum VotoValor {
    N("Não"),
    S("Sim");

    private final String descricao;

    VotoValor(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
