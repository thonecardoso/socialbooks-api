package com.example.socialbooks.resources;

import com.example.socialbooks.domain.Livro;
import com.example.socialbooks.repository.LivroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Livro livro){
        Livro livroSalvo = livroRepository.save(livro);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(livroSalvo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscar(@PathVariable Long id){
        Optional<Livro> livroOptional = livroRepository.findById(id);
        return livroOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        try {
            livroRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livro){
        Livro livroAtualizar = livroRepository.findById(id).get();
        BeanUtils.copyProperties(livro, livroAtualizar,"id");
        livroRepository.save(livroAtualizar);
        return ResponseEntity.ok(livroAtualizar);
    }
}
