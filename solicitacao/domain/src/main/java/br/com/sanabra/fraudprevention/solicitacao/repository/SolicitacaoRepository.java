package br.com.sanabra.fraudprevention.solicitacao.repository;

import br.com.sanabra.fraudprevention.solicitacao.domain.Solicitacao;
import org.springframework.data.repository.CrudRepository;

public interface SolicitacaoRepository extends CrudRepository<Solicitacao, String> {

}
