package com.eduardokp.assembleia.model.entity;

import com.eduardokp.assembleia.model.interfaces.EntityId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidade Usuario
 */
@Data
@Entity
@Table(name = "usuario")
@NoArgsConstructor
public class Usuario implements EntityId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Voto> votos;

    public Usuario(Long id) {
        this.id = id;
    }
}
