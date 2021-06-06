package br.com.sanabra.fraudprevention.solicitacao.exception;

import br.com.sanabra.fraudprevention.solicitacao.exception.base.ErroNegocio;
import br.com.sanabra.fraudprevention.solicitacao.exception.base.SolicitacaoNaoEncontradaException;
import br.com.sanabra.fraudprevention.solicitacao.exception.base.SolicitacaoStatusException;

public class ReportarErro {

    public static ErroNegocio solicitacaoNaoEncontrada(String numeroProtocolo) {
        return new SolicitacaoNaoEncontradaException(String.format("Solicitação com o protocolo = %s não encontrado", numeroProtocolo));
    }

    public static ErroNegocio solicitacaoJaPossuiDecisaoAtribuida(String numeroProtocolo) {
        return new SolicitacaoStatusException(String.format("Solicitação com o protocolo = %s já possui decisão atribuída", numeroProtocolo));
    }

}
