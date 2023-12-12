package com.eduardokp.assembleia.exceptions;

/**
 * Exception lançada ao realizar uma validação de existência de uma entidade no banco
 */
public class EntityNotFoundException extends IllegalArgumentException {
    public final static String MSG = "Entidade não encontrada: %s, código: %s.";

    public EntityNotFoundException(Class classe, Object id) {
        super(String.format(MSG, classe.getName(), id));
    }
}
