package com.eduardokp.assembleia.services;

import com.eduardokp.assembleia.model.dto.UsuarioDTO;
import com.eduardokp.assembleia.model.entity.Usuario;
import com.eduardokp.assembleia.model.mapper.MapperUsuarioEntityDTO;
import com.eduardokp.assembleia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service para a entidade Usuario
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final MapperUsuarioEntityDTO mapperEntityDTO = new MapperUsuarioEntityDTO();

    /**
     * Método em que o Usuario é persistido no banco de dados
     * @param usuarioDTO DTO advindo de requisição da API REST
     * @return A entidade persistida
     */
    @Transactional
    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.save(mapperEntityDTO.toEntity(usuarioDTO));
        return mapperEntityDTO.toDTO(usuario);
    }

    /**
     * Busca pelo código e retorna o DTO
     * @param id codigo do registro
     * @return Registro mapeado para o DTO
     */
    public UsuarioDTO getById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario == null) {
            return null;
        }

        return mapperEntityDTO.toDTO(usuario);
    }

    /**
     * Retorna todos os registros mapeados para DTO
     * @return lista ordenada por Id
     */
    public List<UsuarioDTO> getAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(mapperEntityDTO::toDTO)
                .sorted(Comparator.comparing(UsuarioDTO::getId))
                .collect(Collectors.toList());
    }

}
