package com.eduardokp.assembleia.model.mapper;

import com.eduardokp.assembleia.model.business.Votacao;
import com.eduardokp.assembleia.model.entity.Pauta;
import com.eduardokp.assembleia.model.interfaces.MapperClass;

/**
 * Mapeador comum da classe Pauta para a classe Votacao
 */
public class MapperPautaToVotacao implements MapperClass<Pauta, Votacao> {

    @Override
    public Votacao map(Pauta pauta) {
        Votacao votacao = new Votacao();
        votacao.setPoolId(pauta.getId());
        votacao.setDataInicial(pauta.getDataCriacao());
        votacao.setDataFinal(pauta.getDataTermino());
        return votacao;
    }
}
