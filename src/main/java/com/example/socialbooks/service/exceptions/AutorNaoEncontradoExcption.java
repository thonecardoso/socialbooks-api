package com.example.socialbooks.service.exceptions;

public class AutorNaoEncontradoExcption extends RuntimeException{

    public AutorNaoEncontradoExcption(String mensagem){
        super(mensagem);
    }

    public AutorNaoEncontradoExcption(String mensagem, Throwable causa){
        super(mensagem,causa);
    }
}
