package com.example.socialbooks.service;

import com.example.socialbooks.domain.Comentario;
import com.example.socialbooks.domain.Livro;
import com.example.socialbooks.repository.ComentarioRepository;
import com.example.socialbooks.repository.LivroRepository;
import com.example.socialbooks.service.exceptions.LivroNaoEncontradoExcption;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class LivrosService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public ResponseEntity<Void> salvar(Livro livro) {
        Livro livroSalvo = livroRepository.save(livro);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(livroSalvo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<Void> deletar(Long id) {
        buscarLivro(id);
        livroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Livro> atualizar(Long id, Livro livro) {
        Livro livroAtualizar = buscarLivro(id);
        BeanUtils.copyProperties(livro, livroAtualizar, "id");
        livroRepository.save(livroAtualizar);
        return ResponseEntity.ok(livroAtualizar);
    }


    public ResponseEntity<Livro> buscar(Long id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);
        return livroOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Livro>> listar() {
        CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
        return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livroRepository.findAll());
    }

    public ResponseEntity<Livro> adicionarComentario(Long id, Comentario comentario) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Livro livro = buscarLivro(id);
        comentario.setLivro(livro);
        comentario.setData(new Date());
        comentario.setUsuario(auth.getName());
        comentarioRepository.save(comentario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<List<Comentario>> listarComentarios(Long id) {
        Livro livro = buscarLivro(id);
        return ResponseEntity.ok(livro.getComentarios());
    }

    private Livro buscarLivro(Long id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);
        return livroOptional.orElseThrow(() -> new LivroNaoEncontradoExcption("Livro não encontrado!"));
    }
}
