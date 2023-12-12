package com.eduardokp.assembleia.model.interfaces;

/**
 * Interface genérica para ser implementado por mapeadores de Entidades do banco de dados com classes de retorno na API
 * REST
 *
 * @param <E> Entidade mapeada com banco de dados
 * @param <T> DTO que será retornado nas requisições da API
 */
public interface MapperEntityDTO<E extends EntityId, T extends DTO<E>> {

    /**
     * Transforma o DTO em uma Entidade, fluxo vindo de uma requisição para o banco de dados
     *
     * @param dto DTO esperado na API REST
     * @return classe populada com os dados do DTO
     */
    E toEntity(T dto);
    /**
     * Transforma a Entidade em um DTO, fluxo vindo do banco de dados para retorno em uma requisição
     *
     * @param entity Entidade advinda do banco de dados
     * @return classe populada com os dados do DTO
     */
    T toDTO(E entity);
}
