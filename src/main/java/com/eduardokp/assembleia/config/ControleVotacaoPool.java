package com.eduardokp.assembleia.config;

import com.eduardokp.assembleia.exceptions.MapPoolVotacaoFullException;
import com.eduardokp.assembleia.model.business.Votacao;
import com.eduardokp.assembleia.model.entity.Pauta;
import com.eduardokp.assembleia.model.mapper.MapperPautaToVotacao;
import com.eduardokp.assembleia.utils.LocalDateTimeUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Controla a pool de votações que será manuseada durante funcionamento da API REST
 */
public class ControleVotacaoPool {

    public static long poolSize;

    private final static HashMap<Long, Votacao> mapVotacao = new HashMap<>();

    private final static MapperPautaToVotacao mapperPautaToVotacao = new MapperPautaToVotacao();

    private final static Logger logger = LoggerFactory.getLogger(ControleVotacaoPool.class);

    /**
     * Adiciona a pauta dentro do mapPool de votação, sempre executará saneamento das votações antes de retornar
     * exception
     *
     * @param pauta Pauta que será adicionada no mapPool
     * @throws MapPoolVotacaoFullException em caso onde não existe mais espaço disponível na pool
     * @throws IllegalArgumentException    em caso onde já existe a pauta com votação na pool
     */
    public static void adicionarPauta(Pauta pauta) throws MapPoolVotacaoFullException, IllegalArgumentException {
        if (isMapFull()) {
            saneamento();
        }

        if (isMapFull()) {
            throw new MapPoolVotacaoFullException();
        }

        if (mapVotacao.containsKey(pauta.getId())) {
            throw new IllegalArgumentException("A pauta informada já está em votação.");
        }

        //Garantia que só adicionará pautas com datas de término válidas
        if (isDataTerminoMaiorQueDataAtual(pauta.getDataTermino())) {
            return;
        }

        mapVotacao.put(pauta.getId(), mapperPautaToVotacao.map(pauta));
    }

    /**
     * Remove os registros de votação quando a data final for maior que a data atual
     */
    public static void saneamento() {
        for (Map.Entry<Long, Votacao> mapKey : mapVotacao.entrySet()) {
            if (isDataTerminoMaiorQueDataAtual(mapKey.getValue().getDataFinal())) {
                logger.info("\t Remoção de key do mapPool -> " + mapKey.getKey());
                //Implementação da chamada da Messageria para retorno da votação seria aqui
                mapVotacao.remove(mapKey.getKey());
            }
        }
    }

    /**
     * Retorna valor de Votacao de acordo com a key informada
     *
     * @param votacaoId código da votação
     * @return Instância da votação
     */
    public static Votacao getVotacao(Long votacaoId) {
        return mapVotacao.get(votacaoId);
    }

    /**
     * Retorna texto informativo do tamanho atual do mapPool
     *
     * @return String com informação de tamanho atual do mapPool
     */
    public static String mapPoolToString() {
        return "Tamanho mapPool atual -> " + mapVotacao.keySet().size();
    }

    private static boolean isMapFull() {
        return mapVotacao.keySet().size() >= poolSize;
    }

    private static boolean isDataTerminoMaiorQueDataAtual(LocalDateTime dataTermino) {
        return LocalDateTimeUtilities.isDataMaior(dataTermino, LocalDateTime.now());
    }
}
