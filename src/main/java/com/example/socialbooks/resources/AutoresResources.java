package com.example.socialbooks.resources;

import com.example.socialbooks.domain.Autor;
import com.example.socialbooks.service.AutoresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/autores")
@Api(value = "API Rest Autores")
@CrossOrigin(origins = "*")
public class AutoresResources {

    @Autowired
    private AutoresService autoresService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value = "Retorna uma lista de autores cadastrados")
    public ResponseEntity<List<Autor>> listar(){ return autoresService.listar(); }

    @PostMapping
    @ApiOperation(value = "Cadastra um novo autor e o retorna")
    public ResponseEntity<Autor> salvar(@RequestBody @Valid Autor autor){
        return autoresService.salvar(autor);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna um autor através de uma busca pelo id")
    public ResponseEntity<Autor> buscar(@PathVariable Long id){
        return autoresService.buscar(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Excluí um autor através do seu id")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        return autoresService.deletar(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um autor")
    public ResponseEntity<Autor> atualizar(@PathVariable Long id, @RequestBody Autor autor){
        return autoresService.atualizar(id,autor);
    }


}
