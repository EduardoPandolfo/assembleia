package com.eduardokp.assembleia.controller.application;

import com.eduardokp.assembleia.exceptions.EntityNotFoundException;
import com.eduardokp.assembleia.exceptions.MapPoolVotacaoFullException;
import com.eduardokp.assembleia.exceptions.VotacaoDuplicadaException;
import com.eduardokp.assembleia.exceptions.VotacaoPautaEncerradaException;
import com.eduardokp.assembleia.model.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que armazena os throws das Exceptions da API REST
 */
@RestControllerAdvice
public class ApplicationAdviceController {

    /**
     * Garante que retorna em formato de lista para conseguir indicar mais de um erro encontrado nos objetos enviados
     * para a API REST
     *
     * @param ex excpetion lançada
     * @return DTO de resposta
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO<?> handlerValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }

        return new ResponseDTO<>(errors);
    }

    @ExceptionHandler(MapPoolVotacaoFullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO<?> handlerMapPoolFullException(MapPoolVotacaoFullException ex) {
        return new ResponseDTO<>(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO<?> handlerEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseDTO<>(ex.getMessage());
    }

    @ExceptionHandler(VotacaoDuplicadaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO<?> handlerVotacaoDuplicadaException(VotacaoDuplicadaException ex) {
        return new ResponseDTO<>(ex.getMessage());
    }

    @ExceptionHandler(VotacaoPautaEncerradaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO<?> handlerVotacaoPautaEncerradaException(VotacaoPautaEncerradaException ex) {
        return new ResponseDTO<>(ex.getMessage());
    }
}
