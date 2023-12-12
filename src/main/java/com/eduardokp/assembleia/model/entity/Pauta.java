package com.eduardokp.assembleia.model.entity;

import com.eduardokp.assembleia.model.interfaces.EntityId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidade Pauta
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pauta")
public class Pauta implements EntityId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "datacriacao")
    private LocalDateTime dataCriacao;

    @Column(name = "datatermino")
    private LocalDateTime dataTermino;

    @OneToMany(mappedBy = "pauta", fetch = FetchType.LAZY)
    private List<Voto> votos;

    public Pauta(Long id) {
        this.id = id;
    }

    public Pauta(Long id, LocalDateTime dataCriacao, LocalDateTime dataTermino) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataTermino = dataTermino;
    }

}
