package com.example.socialbooks.resources;

import com.example.socialbooks.domain.Livro;
import com.example.socialbooks.service.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivrosResources {

    @Autowired
    private LivrosService livrosService;

    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        return livrosService.listar();
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Livro livro){
        return livrosService.salvar(livro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscar(@PathVariable Long id){
        return livrosService.buscar(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        return livrosService.deletar(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livro){
        return livrosService.atualizar(id,livro);
    }
}
