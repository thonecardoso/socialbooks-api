package com.example.socialbooks.hendler;

import com.example.socialbooks.domain.DetalhesErro;
import com.example.socialbooks.service.exceptions.AutorExistenteExcption;
import com.example.socialbooks.service.exceptions.AutorNaoEncontradoExcption;
import com.example.socialbooks.service.exceptions.LivroNaoEncontradoExcption;
import org.springframework.dao.DataIntegrityViolationException;
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
    @ExceptionHandler(AutorNaoEncontradoExcption.class)
    public ResponseEntity<DetalhesErro> handleAutorNaoEncontradoExcption(AutorNaoEncontradoExcption e, HttpServletRequest request){

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(404L);
        erro.setTitulo("O Autor não pôde ser encontrado");
        erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    @ExceptionHandler(AutorExistenteExcption.class)
    public ResponseEntity<DetalhesErro> handleAutorExistenteExcption(AutorExistenteExcption e, HttpServletRequest request){

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(409L);
        erro.setTitulo("Autor já existente!");
        erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest request){

        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(400L);
        erro.setTitulo("Requisição Inválida!");
        erro.setMensagemDesenvolvedor("http://erros.socialbooks.com/400");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
