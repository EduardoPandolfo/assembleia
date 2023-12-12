package com.eduardokp.assembleia.model.entity;

import com.eduardokp.assembleia.model.enums.VotoValor;
import com.eduardokp.assembleia.model.interfaces.EntityId;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidade Voto
 */
@Data
@Entity
@Table(name = "voto")
public class Voto implements EntityId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "pautaid", nullable = false)
    private Pauta pauta;

    @Enumerated(EnumType.STRING)
    @Column(name = "valor", length = 1, nullable = false)
    private VotoValor valor;
}
