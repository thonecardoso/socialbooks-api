package com.example.socialbooks.resources;

import com.example.socialbooks.domain.Comentario;
import com.example.socialbooks.domain.Livro;
import com.example.socialbooks.service.LivrosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/livros")
@Api(value = "API Rest Livros")
@CrossOrigin(origins = "*")
public class LivrosResources {

    @Autowired
    private LivrosService livrosService;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de livros cadastrados")
    public ResponseEntity<List<Livro>> listar() {
        return livrosService.listar();
    }

    @PostMapping
    @ApiOperation(value = "Cadastra um livro na base de dados e o retorna")
    public ResponseEntity<Void> salvar(@RequestBody Livro livro){
        return livrosService.salvar(livro);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Retorna um livro através de uma busca pelo id")
    public ResponseEntity<Livro> buscar(@PathVariable Long id){
        return livrosService.buscar(id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Excluí um livro através do seu id")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        return livrosService.deletar(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um livro")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livro){
        return livrosService.atualizar(id,livro);
    }

    @PostMapping("/{id}/comentarios")
    @ApiOperation(value = "Adiciona um comentário a um livro")
    public ResponseEntity<Livro> adicionarComentario(@PathVariable Long id, @RequestBody Comentario comentario){
        return livrosService.adicionarComentario(id,comentario);
    }

    @GetMapping("/{id}/comentarios")
    @ApiOperation(value = "Retorna uma lista de comentários de um livro")
    public ResponseEntity<List<Comentario>> adicionarComentario(@PathVariable Long id){
        return livrosService.listarComentarios(id);
    }


}
