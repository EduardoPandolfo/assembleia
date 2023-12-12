package com.eduardokp.assembleia.model.mapper;

import com.eduardokp.assembleia.model.dto.UsuarioDTO;
import com.eduardokp.assembleia.model.entity.Usuario;
import com.eduardokp.assembleia.model.interfaces.MapperEntityDTO;

/**
 * Classe de Mapeamento para a Entidade Pauta e seu respectivo DTO
 */
public class MapperUsuarioEntityDTO implements MapperEntityDTO<Usuario, UsuarioDTO> {

    @Override
    public Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        return usuario;
    }

    @Override
    public UsuarioDTO toDTO(Usuario entity) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;
    }
}
