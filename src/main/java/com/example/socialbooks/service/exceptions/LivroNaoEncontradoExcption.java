package com.example.socialbooks.service.exceptions;

public class LivroNaoEncontradoExcption extends RuntimeException{

    public LivroNaoEncontradoExcption(String mensagem){
        super(mensagem);
    }

    public LivroNaoEncontradoExcption(String mensagem, Throwable causa){
        super(mensagem,causa);
    }
}
