package br.com.sanabra.fraudprevention.solicitacao.domain;

import br.com.sanabra.fraudprevention.solicitacao.events.SolicitacaoStatusEvent;
import br.com.sanabra.fraudprevention.solicitacao.events.SolicitacaoStatusEventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static br.com.sanabra.fraudprevention.solicitacao.exception.ReportarErro.solicitacaoJaPossuiDecisaoAtribuida;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Entity
public class Solicitacao extends AbstractAggregateRoot<Solicitacao> implements Serializable {

    @Id
    private String numeroProtocolo;
    private String canal;
    private String produto;
    private SolicitacaoStatusEnum status;
    private DecisaoEnum decisao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;

    public Solicitacao() {
        this.iniciarSolicitacao();
    }

    public void iniciarSolicitacao() {
        this.numeroProtocolo = UUID.randomUUID().toString();
        this.status = SolicitacaoStatusEnum.PENDENTE;
        this.dataCriacao = LocalDateTime.now();
        registerEvent(new SolicitacaoStatusEvent(SolicitacaoStatusEventType.CRIADO));
    }

    public void atribuirDecisao(DecisaoEnum decisao) {
        if (SolicitacaoStatusEnum.CONCLUIDO.equals(this.status)) {
            throw solicitacaoJaPossuiDecisaoAtribuida(this.numeroProtocolo);
        }
        this.status = SolicitacaoStatusEnum.CONCLUIDO;
        this.dataConclusao = LocalDateTime.now();
        this.decisao = decisao;
        registerEvent(new SolicitacaoStatusEvent(SolicitacaoStatusEventType.ANALISADO));
    }
}
