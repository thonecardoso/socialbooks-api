package com.example.socialbooks.service.exceptions;

public class AutorExistenteExcption extends RuntimeException{

    public AutorExistenteExcption(String mensagem){
        super(mensagem);
    }

    public AutorExistenteExcption(String mensagem, Throwable causa){
        super(mensagem,causa);
    }
}
