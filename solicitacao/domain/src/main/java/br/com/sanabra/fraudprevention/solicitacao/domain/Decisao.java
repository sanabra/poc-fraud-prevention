package br.com.sanabra.fraudprevention.solicitacao.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Decisao {

    private DecisaoEnum decisao;

}
