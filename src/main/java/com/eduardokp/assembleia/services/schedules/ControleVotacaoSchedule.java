package com.eduardokp.assembleia.services.schedules;

import com.eduardokp.assembleia.services.PautaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Classe que realiza a limpeza da pool de pautas, removendo as pautas que já tiveram seu tempo finalizado
 */
@Component
public class ControleVotacaoSchedule {

    /**
     * 60.000 milissegundos -> 1 minuto
     */
    @Scheduled(fixedRate = 60000)
    public void verificarTempoVotacao() {
    }

}
