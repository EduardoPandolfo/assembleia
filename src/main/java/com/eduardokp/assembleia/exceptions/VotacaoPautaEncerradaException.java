package com.eduardokp.assembleia.exceptions;

public class VotacaoPautaEncerradaException extends IllegalArgumentException {
    public final static String MSG = "Votação da pauta já encerrada!";

    public VotacaoPautaEncerradaException() {
        super(MSG);
    }
}
