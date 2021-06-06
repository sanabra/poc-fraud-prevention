package br.com.sanabra.fraudprevention.solicitacao.presentation;

import br.com.sanabra.fraudprevention.solicitacao.mapper.SolicitacaoMapper;
import br.com.sanabra.fraudprevention.solicitacao.representation.DecisaoRepresentation;
import br.com.sanabra.fraudprevention.solicitacao.representation.NumeroProtocoloRepresentation;
import br.com.sanabra.fraudprevention.solicitacao.representation.SolicitacaoRepresentation;
import br.com.sanabra.fraudprevention.solicitacao.representation.StatusRepresentation;
import br.com.sanabra.fraudprevention.solicitacao.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoApiDelegateImpl implements SolicitacaoApiDelegate {

    @Autowired
    private SolicitacaoMapper mapper;

    @Autowired
    private SolicitacaoService service;

    @Override
    public ResponseEntity<NumeroProtocoloRepresentation> postSolicitacao(SolicitacaoRepresentation solicitacaoRepresentation) {
        return new ResponseEntity<>(
                mapper.toNumeroProtocoloRepresentation(
                        service.criar(mapper.toDomain(solicitacaoRepresentation))),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StatusRepresentation> getSolicitacaoStatus(String numeroProtocolo) {
        return new ResponseEntity<>(
                mapper.toStatusRepresentation(
                        service.consultarSolicitacao(numeroProtocolo)),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> postSolicitacaoDecisao(String numeroProtocolo, DecisaoRepresentation decisaoRepresentation) {
        service.atribuirDecisao(numeroProtocolo, mapper.toDomain(decisaoRepresentation));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
