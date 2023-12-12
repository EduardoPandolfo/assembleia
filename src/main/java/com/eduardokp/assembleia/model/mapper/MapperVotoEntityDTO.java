package com.eduardokp.assembleia.model.mapper;

import com.eduardokp.assembleia.model.dto.VotoDTO;
import com.eduardokp.assembleia.model.entity.Pauta;
import com.eduardokp.assembleia.model.entity.Usuario;
import com.eduardokp.assembleia.model.entity.Voto;
import com.eduardokp.assembleia.model.interfaces.MapperEntityDTO;

/**
 * Classe de Mapeamento para a Entidade Voto e seu respectivo DTO
 */
public class MapperVotoEntityDTO implements MapperEntityDTO<Voto, VotoDTO> {

    @Override
    public Voto toEntity(VotoDTO dto) {
        Voto voto = new Voto();
        voto.setPauta(new Pauta(dto.getPautaId()));
        voto.setUsuario(new Usuario(dto.getUsuarioId()));
        voto.setValor(dto.getValor());
        return voto;
    }

    @Override
    public VotoDTO toDTO(Voto entity) {
        VotoDTO votoDTO = new VotoDTO();
        votoDTO.setId(entity.getId());
        return votoDTO;
    }
}
