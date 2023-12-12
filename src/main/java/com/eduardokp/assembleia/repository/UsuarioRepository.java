package com.eduardokp.assembleia.repository;

import com.eduardokp.assembleia.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Usuario
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
