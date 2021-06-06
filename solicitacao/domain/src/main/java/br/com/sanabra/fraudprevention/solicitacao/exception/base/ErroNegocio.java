package br.com.sanabra.fraudprevention.solicitacao.exception.base;

public abstract class ErroNegocio extends RuntimeException {

    public ErroNegocio(String mensagem) {
        super(mensagem);
    }
}
