package br.com.sanabra.fraudprevention.solicitacao.service;

import br.com.sanabra.fraudprevention.solicitacao.domain.Decisao;
import br.com.sanabra.fraudprevention.solicitacao.domain.DecisaoEnum;
import br.com.sanabra.fraudprevention.solicitacao.domain.Solicitacao;
import br.com.sanabra.fraudprevention.solicitacao.exception.ReportarErro;
import br.com.sanabra.fraudprevention.solicitacao.exception.base.ErroNegocio;
import br.com.sanabra.fraudprevention.solicitacao.repository.SolicitacaoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class SolicitacaoServiceTest {

    @InjectMocks
    SolicitacaoService solicitacaoService;

    @Mock
    SolicitacaoRepository solicitacaoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void deveCriarSolicitacaoTest() {
        Solicitacao solicitacao = new Solicitacao();

        given(solicitacaoRepository.save(solicitacao)).willReturn(solicitacao);
        solicitacaoService.criar(solicitacao);
        then(solicitacaoRepository).should().save(solicitacao);
    }

    @Test
    public void deveConsultarSolicitacaoTest() {
        Solicitacao solicitacao = Mockito.mock(Solicitacao.class);
        String numeroProtocolo = UUID.randomUUID().toString();

        given(solicitacaoRepository.findById(numeroProtocolo)).willReturn(Optional.of(solicitacao));
        solicitacaoService.consultarSolicitacao(numeroProtocolo);
        then(solicitacaoRepository).should().findById(numeroProtocolo);
    }

    @Test
    public void deveGerarExcecaoQuandoConsultaENulaTest() {
        String numeroProtocolo = UUID.randomUUID().toString();

        given(solicitacaoRepository.findById(numeroProtocolo)).willReturn(Optional.<Solicitacao>empty());
        ErroNegocio thrown = assertThrows(ErroNegocio.class,
                () -> solicitacaoService.consultarSolicitacao(numeroProtocolo)
        );
        assertEquals(ReportarErro.solicitacaoNaoEncontrada(numeroProtocolo).getMessage(),
                thrown.getMessage());
    }

    @Test
    public void deveAtribuirDecisaoTest() {
        Solicitacao solicitacao = new Solicitacao();
        Decisao decisao = Decisao.builder()
                .decisao(DecisaoEnum.APROVADO)
                .build();

        given(solicitacaoRepository.findById(solicitacao.getNumeroProtocolo())).willReturn(Optional.of(solicitacao));
        solicitacaoService.atribuirDecisao(solicitacao.getNumeroProtocolo(), decisao);
        then(solicitacaoRepository).should().save(solicitacao);
    }
}