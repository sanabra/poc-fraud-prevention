package br.com.sanabra.fraudprevention.solicitacao.mapper;

import br.com.sanabra.fraudprevention.solicitacao.domain.Decisao;
import br.com.sanabra.fraudprevention.solicitacao.domain.Solicitacao;
import br.com.sanabra.fraudprevention.solicitacao.representation.DecisaoRepresentation;
import br.com.sanabra.fraudprevention.solicitacao.representation.NumeroProtocoloRepresentation;
import br.com.sanabra.fraudprevention.solicitacao.representation.SolicitacaoRepresentation;
import br.com.sanabra.fraudprevention.solicitacao.representation.StatusRepresentation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SolicitacaoMapper {

    Solicitacao toDomain(SolicitacaoRepresentation source);

    NumeroProtocoloRepresentation toNumeroProtocoloRepresentation(Solicitacao solicitacao);

    StatusRepresentation toStatusRepresentation(Solicitacao solicitacao);

    Decisao toDomain(DecisaoRepresentation decisaoRepresentation);
}
