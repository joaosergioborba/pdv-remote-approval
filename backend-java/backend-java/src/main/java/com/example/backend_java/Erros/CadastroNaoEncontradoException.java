package com.example.backend_java.Erros;

public class CadastroNaoEncontradoException extends RuntimeException{

    public CadastroNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
