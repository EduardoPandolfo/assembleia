package com.eduardokp.assembleia.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO com resultado de Pauta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotacaoDTO {
    private Long pautaId;
    private String pautaTitulo;
    private String dataTermino;
    private Long votosPositivos;
    private Long votosNegativos;
}
