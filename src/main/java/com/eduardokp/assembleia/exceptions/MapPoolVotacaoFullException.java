package com.eduardokp.assembleia.exceptions;

/**
 * Exception lançada ao tentar adicionar um registro na pool informando que não existe mais espaço disponível
 */
public class MapPoolVotacaoFullException extends RuntimeException {
    public final static String MSG = "Não foi possível inserir nova pauta, lista de votação está cheia.";

    public MapPoolVotacaoFullException() {
        super(MSG);
    }
}