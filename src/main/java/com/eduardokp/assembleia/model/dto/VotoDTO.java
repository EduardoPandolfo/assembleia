package com.eduardokp.assembleia.model.dto;

import com.eduardokp.assembleia.model.entity.Voto;
import com.eduardokp.assembleia.model.enums.VotoValor;
import com.eduardokp.assembleia.model.interfaces.DTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * DTO para a entidade Voto
 */
@Data
public class VotoDTO implements DTO<Voto> {

    private Long id;

    @NotNull(message = "Código da pauta é obrigatório")
    private Long pautaId;

    @NotNull(message = "Código do usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "Valor é obrigatório")
    private VotoValor valor;

}