package com.example.socialbooks.hendler;

import com.example.socialbooks.domain.DetalhesErro;
import com.example.socialbooks.service.exceptions.LivroNaoEncontradoExcption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(LivroNaoEncontradoExcption.class)
    public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException(LivroNaoEncontradoExcption e, HttpServletRequest request){

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404L);
        erro.setTitulo("O livro não pôde ser encontrado");
        erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
