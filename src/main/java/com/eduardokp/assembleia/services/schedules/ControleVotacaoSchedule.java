package com.eduardokp.assembleia.services.schedules;

import com.eduardokp.assembleia.config.ControleVotacaoPool;
import com.eduardokp.assembleia.model.entity.Pauta;
import com.eduardokp.assembleia.services.PautaService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classe que realiza a limpeza da pool de pautas, removendo as pautas que já tiveram seu tempo finalizado
 */
@Component
public class ControleVotacaoSchedule {

    @Autowired
    private PautaService pautaService;

    private final Logger logger = LoggerFactory.getLogger(ControleVotacaoSchedule.class);

    /**
     * Inicializa a mapPool com dados de pautas ativas no banco;
     */
    @PostConstruct
    public void init() {
        logger.info("\t Executando pesquisa de pautas Ativas");
        List<Pauta> pautasAtivas = pautaService.pesquisarAtivas();

        for(Pauta pauta : pautasAtivas) {
            try{
                //garante que passará por todos os registros tentando adiciona-los
                ControleVotacaoPool.adicionarPauta(pauta);
            }catch (Exception e){
                //do nothing
            }
        }
    }

    /**
     * 60.000 milissegundos -> 1 minuto
     */
    @Scheduled(fixedRate = 60000)
    public void verificarTempoVotacao() {
        logger.info("\tExecutando saneamento mapPool");
        ControleVotacaoPool.saneamento();
        logger.info("\tSaneamento executado: " + ControleVotacaoPool.mapPoolToString());
    }

}

