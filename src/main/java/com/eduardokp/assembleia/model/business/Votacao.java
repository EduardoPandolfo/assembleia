package com.eduardokp.assembleia.model.business;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Objeto utilizado na pool de pautas ativas para votação
 */
@Data
public class Votacao {
    private Long poolId;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
}
