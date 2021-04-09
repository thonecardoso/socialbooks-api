package com.example.socialbooks.service;

import com.example.socialbooks.domain.Livro;
import com.example.socialbooks.repository.LivroRepository;
import com.example.socialbooks.service.exceptions.LivroNaoEncontradoExcption;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class LivrosService {

    @Autowired
    private LivroRepository livroRepository;

    public ResponseEntity<Void> salvar(Livro livro) {
        Livro livroSalvo = livroRepository.save(livro);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(livroSalvo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<Void> deletar(Long id) {

        Optional<Livro> livroOptional = livroRepository.findById(id);
        livroOptional.orElseThrow(() -> new LivroNaoEncontradoExcption("Livro não encontrado!"));
        livroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Livro> atualizar(Long id, Livro livro) {
        Livro livroAtualizar = livroRepository.findById(id).get();
        BeanUtils.copyProperties(livro, livroAtualizar, "id");
        livroRepository.save(livroAtualizar);
        return ResponseEntity.ok(livroAtualizar);
    }

    public ResponseEntity<Livro> buscar(Long id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);
        return livroOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.findAll());
    }
}
