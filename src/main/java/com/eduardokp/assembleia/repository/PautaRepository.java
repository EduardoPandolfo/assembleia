package com.eduardokp.assembleia.repository;

import com.eduardokp.assembleia.model.entity.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Pauta
 */
@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
