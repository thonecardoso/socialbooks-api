package com.example.socialbooks.service;

import com.example.socialbooks.domain.Autor;
import com.example.socialbooks.domain.Livro;
import com.example.socialbooks.repository.AutoresRepository;
import com.example.socialbooks.service.exceptions.AutorExistenteExcption;
import com.example.socialbooks.service.exceptions.AutorNaoEncontradoExcption;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class AutoresService {

    @Autowired
    private AutoresRepository autoresRepository;

    public ResponseEntity<List<Autor>> listar() {
        return ResponseEntity.ok(autoresRepository.findAll());
    }

    public ResponseEntity<Autor> salvar(Autor autor) {
        if(autor.getId()!=null){
            Optional<Autor> autorOptional = autoresRepository.findById(autor.getId());
            if(autorOptional.isPresent()){
                throw new AutorExistenteExcption("Conflito, autor já cadastrado.");
            }
        }


        Autor autorSalvo = autoresRepository.save(autor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(autorSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(autorSalvo);
    }

    public ResponseEntity<Autor> buscar(Long id) {
        Autor autor = buscarAutor(id);
        return ResponseEntity.ok(autor);
    }

    private Autor buscarAutor(Long id) {
        Optional<Autor> autorOptional = autoresRepository.findById(id);
        return autorOptional.orElseThrow(()-> new AutorNaoEncontradoExcption("Autor não encontrado!"));
    }

    public ResponseEntity<Void> deletar(Long id) {
        Autor autor = buscarAutor(id);
        autoresRepository.delete(autor);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<Autor> atualizar(Long id, Autor autor) {
        Autor autorAtualizar = buscarAutor(id);
        BeanUtils.copyProperties(autor, autorAtualizar, "id");
        return ResponseEntity.ok(autoresRepository.save(autorAtualizar));
    }
}
