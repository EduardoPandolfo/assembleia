package com.eduardokp.assembleia.model.dto;

import com.eduardokp.assembleia.model.entity.Usuario;
import com.eduardokp.assembleia.model.interfaces.DTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO para a entidade Usuario
 */
@Data
public class UsuarioDTO implements DTO<Usuario> {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

}