package com.eduardokp.assembleia.model.dto;

import com.eduardokp.assembleia.model.entity.Pauta;
import com.eduardokp.assembleia.model.enums.TipoUnidadeTempo;
import com.eduardokp.assembleia.model.interfaces.DTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para a entidade Pauta
 */
@Data
@NoArgsConstructor
public class PautaDTO implements DTO<Pauta> {

    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String descricao;

    private TipoUnidadeTempo tipoUnidadeTempo;

    @Min(value = 1)
    private Long quantidadeTempo;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataTermino;
}