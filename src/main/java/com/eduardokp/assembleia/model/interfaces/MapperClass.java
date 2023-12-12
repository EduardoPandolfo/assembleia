package com.eduardokp.assembleia.model.interfaces;

/**
 * Interface genérica que será utilizada no mapeamento de classes dentro do projeto
 * @param <T> classe que será utilizada no mapeamento
 * @param <E> classe resultante do mapeamento
 */
public interface MapperClass <T, E> {
    E map(T t);
}
