package com.eduardokp.assembleia.services;

import com.eduardokp.assembleia.config.ControleVotacaoPool;
import com.eduardokp.assembleia.model.dto.PautaDTO;
import com.eduardokp.assembleia.model.dto.VotacaoDTO;
import com.eduardokp.assembleia.model.entity.Pauta;
import com.eduardokp.assembleia.model.mapper.MapperPautaEntityDTO;
import com.eduardokp.assembleia.repository.PautaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service para a Entidade Pauta
 */
@Service
public class PautaService {

    @Autowired
    private EntityManager em;

    @Autowired
    private PautaRepository pautaRepository;

    private final MapperPautaEntityDTO mapperEntityDTO = new MapperPautaEntityDTO();

    private static final String QUERY_PAUTAS_ATIVAS = "select p.id, p.dataCriacao, p.dataTermino " +
            " from Pauta p " +
            " where p.dataTermino > current_date " +
            " order by 1 asc";

    private static final String QUERY_RESULTADO_PAUTA = "select " +
            " p.id," +
            " p.titulo, " +
            " TO_CHAR(p.datatermino, 'DD/MM/YYYY HH24:MI:SS'), " +
            " count(1) filter (where v.valor = 'S') as positivo," +
            " count(1) filter (where v.valor = 'N') as negativo" +
            " from pauta p \n" +
            " left join voto v on v.pautaid = p.id" +
            " where p.id = :pautaId " +
            " group by 1,2,3" +
            " order by 1,2,3;";

    /**
     * Método que persiste a Pauta no banco de dados, adiciona no MapPool o registro de pauta
     *
     * @param pautaDTO DTO advindo de requisição da API REST
     * @return A entidade persistida
     */
    @Transactional
    public PautaDTO create(PautaDTO pautaDTO) {
        Pauta pauta = pautaRepository.save(mapperEntityDTO.toEntity(pautaDTO));
        ControleVotacaoPool.adicionarPauta(pauta);
        return mapperEntityDTO.toDTO(pauta);
    }

    /**
     * Busca pelo código e retorna o DTO
     *
     * @param id codigo do registro
     * @return Registro mapeado para o DTO
     */
    public PautaDTO getById(Long id) {
        Pauta pauta = pautaRepository.findById(id).orElse(null);
        if (pauta == null) {
            return null;
        }

        return mapperEntityDTO.toDTO(pauta);
    }

    /**
     * Retorna todos os registros mapeados para DTO
     *
     * @return lista ordenada por Id
     */
    public List<PautaDTO> getAll() {
        List<Pauta> pautas = pautaRepository.findAll();

        return pautas.stream()
                .map(mapperEntityDTO::toDTO)
                .sorted(Comparator.comparing(PautaDTO::getId))
                .collect(Collectors.toList());
    }

    /**
     * Retorna todos os registros registros ativos mapeados para DTO
     *
     * @return lista ordenada por Id
     */
    public List<Pauta> pesquisarAtivas() {
        TypedQuery<Pauta> query = em.createQuery(QUERY_PAUTAS_ATIVAS, Pauta.class);
        return query.getResultList();
    }

    /**
     * Retorna dados de votação para a pauta
     *
     * @param pautaId código da pauta
     * @return DTO com resultado da votação
     */
    public VotacaoDTO pesquisarResultadoPauta(Long pautaId) {
        Query query = em.createNativeQuery(QUERY_RESULTADO_PAUTA, VotacaoDTO.class);
        query.setParameter("pautaId", pautaId);
        return (VotacaoDTO) query.getSingleResult();
    }
}