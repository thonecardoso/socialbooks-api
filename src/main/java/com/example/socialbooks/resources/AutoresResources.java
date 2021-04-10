package com.example.socialbooks.resources;

import com.example.socialbooks.domain.Autor;
import com.example.socialbooks.service.AutoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutoresResources {

    @Autowired
    private AutoresService autoresService;

    @GetMapping
    public ResponseEntity<List<Autor>> listar(){ return autoresService.listar(); }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid Autor autor){
        return autoresService.salvar(autor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscar(@PathVariable Long id){
        return autoresService.buscar(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        return autoresService.deletar(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizar(@PathVariable Long id, @RequestBody Autor autor){
        return autoresService.atualizar(id,autor);
    }


}
