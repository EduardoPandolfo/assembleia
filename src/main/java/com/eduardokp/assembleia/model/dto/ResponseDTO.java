package com.eduardokp.assembleia.model.dto;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * Classe DTO de resposta padrão da API REST
 *
 * @param <T> objeto de retorno, normalmente uma entidade
 */
@Getter
public class ResponseDTO<T> {
    private final List<String> messages;
    private final T data;

    public ResponseDTO(T data, String... messages) {
        this.data = data;
        this.messages = Arrays.asList(messages);
    }
}

