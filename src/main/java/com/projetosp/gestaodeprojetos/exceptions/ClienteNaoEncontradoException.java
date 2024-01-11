package com.projetosp.gestaodeprojetos.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
