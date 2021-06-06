package br.com.sanabra.fraudprevention.solicitacao.domain;

import br.com.sanabra.fraudprevention.solicitacao.exception.ReportarErro;
import br.com.sanabra.fraudprevention.solicitacao.exception.base.ErroNegocio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SolicitacaoTest {

    @Test
    public void deveCriarSolicitacaoComStatusPendenteTest() {
        Solicitacao solicitacao = new Solicitacao();
        assertEquals(SolicitacaoStatusEnum.PENDENTE, solicitacao.getStatus());
        assertNotNull(solicitacao.getNumeroProtocolo());
        assertNotNull(solicitacao.getDataCriacao());
    }

    @Test
    public void deveAtribuirDecisaoQuandoStatusPendenteTest() {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.atribuirDecisao(DecisaoEnum.APROVADO);
        assertNotNull(solicitacao.getDataConclusao());
        assertEquals(DecisaoEnum.APROVADO, solicitacao.getDecisao());
    }

    @Test
    public void naoDeveAtribuirDecisaoQuandoStatusConcluidoTest() {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.atribuirDecisao(DecisaoEnum.APROVADO);

        ErroNegocio thrown = assertThrows(ErroNegocio.class,
                () -> solicitacao.atribuirDecisao(DecisaoEnum.REPROVADO)
        );
        assertEquals(DecisaoEnum.APROVADO, solicitacao.getDecisao());
        assertEquals(ReportarErro.solicitacaoJaPossuiDecisaoAtribuida(
                solicitacao.getNumeroProtocolo()).getMessage(),
                thrown.getMessage());
        assertNotNull(solicitacao.getDataConclusao());
    }
}