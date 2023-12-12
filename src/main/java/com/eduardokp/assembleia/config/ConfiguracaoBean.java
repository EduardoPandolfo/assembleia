package com.eduardokp.assembleia.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Inicializa variável static dentro do controle da pool de votação
 */
@Configuration
public class ConfiguracaoBean {

    @Value("${config.votacao.mapPool.size}")
    private long poolSize;

    @PostConstruct
    public void init() {
        ControleVotacaoPool.poolSize = poolSize;
    }
}
