package br.com.sanabra.fraudprevention.solicitacao.events;

import java.util.UUID;

public class SolicitacaoStatusEvent {

    private final String eventId = UUID.randomUUID().toString();
    private final String eventClassName = getClass().getName();
    private final SolicitacaoStatusEventType eventType;

    public SolicitacaoStatusEvent(SolicitacaoStatusEventType eventType) {
        this.eventType = eventType;
    }

    public boolean isCriado() {
        return SolicitacaoStatusEventType.CRIADO.equals(eventType);
    }

    public boolean isAnalisado() {
        return SolicitacaoStatusEventType.ANALISADO.equals(eventType);
    }
}
