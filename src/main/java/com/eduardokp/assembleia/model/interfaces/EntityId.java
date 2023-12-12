package com.eduardokp.assembleia.model.interfaces;

/**
 * Interface genérica para classes que são Entidades
 * @param <T> tipagem do campo Id
 */
public interface EntityId<T> {
    T getId();
    void setId(T id);
}
