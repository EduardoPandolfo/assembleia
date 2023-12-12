package com.eduardokp.assembleia.services;

import com.eduardokp.assembleia.config.ControleVotacaoPool;
import com.eduardokp.assembleia.exceptions.EntityNotFoundException;
import com.eduardokp.assembleia.exceptions.VotacaoDuplicadaException;
import com.eduardokp.assembleia.model.business.Votacao;
import com.eduardokp.assembleia.model.dto.VotoDTO;
import com.eduardokp.assembleia.model.entity.Pauta;
import com.eduardokp.assembleia.model.entity.Usuario;
import com.eduardokp.assembleia.model.entity.Voto;
import com.eduardokp.assembleia.model.mapper.MapperVotoEntityDTO;
import com.eduardokp.assembleia.repository.UsuarioRepository;
import com.eduardokp.assembleia.repository.VotoRepository;
import com.eduardokp.assembleia.utils.LocalDateTimeUtilities;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service para a entidade Voto
 */
@Service
public class VotoService {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final MapperVotoEntityDTO mapperEntityDTO = new MapperVotoEntityDTO();

    /**
     * Método em que o Voto é persistido no banco de dados
     *
     * @param votoDTO DTO advindo de requisição da API REST
     * @return A entidade persistida
     */
    @Transactional
    public VotoDTO create(VotoDTO votoDTO) {
        Voto voto = mapperEntityDTO.toEntity(votoDTO);

        validarPauta(voto);
        validarUsuario(voto);
        validarVotoDuplicado(voto);

        voto = votoRepository.save(voto);

        return mapperEntityDTO.toDTO(voto);
    }

    /**
     * Valida se a pauta existe E se a mesma tem a data de termino de votação menor que a data atual
     *
     * @param voto utilizado para validar a pauta
     */
    private void validarPauta(Voto voto) {
        Votacao votacao = ControleVotacaoPool.getVotacao(voto.getPauta().getId());

        if (votacao == null) {
            throw new EntityNotFoundException(Pauta.class, voto.getPauta().getId());
        }

        if (LocalDateTimeUtilities.isDataMaior(votacao.getDataFinal(), LocalDateTime.now())) {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Valida se existe outro voto para o usuário na mesma pauta
     *
     * @param voto utilizado para validar usuário
     */
    private void validarUsuario(Voto voto) {
        Usuario usuarioById = usuarioRepository.findById(voto.getUsuario().getId()).orElse(null);

        if (usuarioById == null) {
            throw new EntityNotFoundException(Usuario.class, voto.getUsuario().getId());
        }
    }

    /**
     * Valida se o voto é único para o usuário na pauta
     *
     * @param voto utilizado para validar voto
     */
    private void validarVotoDuplicado(Voto voto) {
        List<Voto> duplicados = votoRepository.findByUsuarioIdAndPautaId(voto.getUsuario().getId(),
                voto.getPauta().getId());

        if (!duplicados.isEmpty()) {
            throw new VotacaoDuplicadaException();
        }
    }
}

