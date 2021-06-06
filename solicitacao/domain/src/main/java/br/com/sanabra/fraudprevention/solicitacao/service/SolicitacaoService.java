package br.com.sanabra.fraudprevention.solicitacao.service;

import br.com.sanabra.fraudprevention.solicitacao.domain.Decisao;
import br.com.sanabra.fraudprevention.solicitacao.domain.Solicitacao;
import br.com.sanabra.fraudprevention.solicitacao.exception.base.SolicitacaoNaoEncontradaException;
import br.com.sanabra.fraudprevention.solicitacao.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static br.com.sanabra.fraudprevention.solicitacao.exception.ReportarErro.solicitacaoNaoEncontrada;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Transactional
    public Solicitacao criar(Solicitacao solicitacao) throws SolicitacaoNaoEncontradaException {
        solicitacao.iniciarSolicitacao();
        return solicitacaoRepository.save(solicitacao);
    }

    @Transactional
    public void atribuirDecisao(String numeroProtocolo, Decisao decisao) {
        Solicitacao solicitacao = consultarSolicitacao(numeroProtocolo);
        solicitacao.atribuirDecisao(decisao.getDecisao());
        solicitacaoRepository.save(solicitacao);
    }

    public Solicitacao consultarSolicitacao(String numeroProtocolo) {
        Optional<Solicitacao> resultado = solicitacaoRepository.findById(numeroProtocolo);
        if (resultado.isEmpty())
            throw solicitacaoNaoEncontrada(numeroProtocolo);
        return resultado.get();
    }
}
