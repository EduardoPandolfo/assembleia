package com.eduardokp.assembleia.repository;

import com.eduardokp.assembleia.model.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório para a entidade Voto
 */
@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

}
