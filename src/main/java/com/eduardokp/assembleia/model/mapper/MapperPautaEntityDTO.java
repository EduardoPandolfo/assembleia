package com.eduardokp.assembleia.model.mapper;

import com.eduardokp.assembleia.model.dto.PautaDTO;
import com.eduardokp.assembleia.model.entity.Pauta;
import com.eduardokp.assembleia.model.interfaces.MapperEntityDTO;

import java.time.LocalDateTime;

/**
 * Classe de Mapeamento para a Entidade Pauta e seu respectivo DTO
 */
public class MapperPautaEntityDTO implements MapperEntityDTO<Pauta, PautaDTO> {

    @Override
    public Pauta toEntity(PautaDTO dto) {
        Pauta pauta = new Pauta();
        pauta.setTitulo(dto.getTitulo());
        pauta.setDescricao(dto.getDescricao());

        LocalDateTime dataAtual = LocalDateTime.now();
        pauta.setDataCriacao(dataAtual);
        pauta.setDataTermino(montaDataTerminoVotacao(dto, dataAtual));

        return pauta;
    }

    @Override
    public PautaDTO toDTO(Pauta entity) {
        PautaDTO dto = new PautaDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescricao(entity.getDescricao());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setDataTermino(entity.getDataTermino());
        return dto;
    }


    /**
     * Calcula quando será o término de acordo com dados do DTO da Pauta, por padrão é considerado 1 minuto
     *
     * @param dto       Objeto da requisição de criação de pauta
     * @param dataAtual data de criação da pauta
     * @return Data atual com a adição do intervalo
     */
    private LocalDateTime montaDataTerminoVotacao(PautaDTO dto, LocalDateTime dataAtual) {
        switch (dto.getTipoUnidadeTempo()) {
            case MINUTO:
                return dataAtual.plusMinutes(dto.getQuantidadeTempo());
            case DIA:
                return dataAtual.plusDays(dto.getQuantidadeTempo());
            case SEMANA:
                return dataAtual.plusWeeks(dto.getQuantidadeTempo());
            default:
                //do nothing
        }

        return dataAtual.plusMinutes(1L);
    }
}
