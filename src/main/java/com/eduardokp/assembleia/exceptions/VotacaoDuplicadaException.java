package com.eduardokp.assembleia.exceptions;

/**
 * Exception lançada para quando já existir um voto cadastrado para mesmo usuário e pauta
 */
public class VotacaoDuplicadaException extends IllegalArgumentException {
    public final static String MSG = "Já existe voto para mesmo usuário e pauta informado.";

    public VotacaoDuplicadaException() {
        super(MSG);
    }
}

