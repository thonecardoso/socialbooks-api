package com.example.socialbooks.resources;

import com.example.socialbooks.domain.Livro;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class LivrosResources {

    @GetMapping("/livros")
    public List<Livro> listar() {

       Livro l1 = new Livro("Rest Aplicado");
       Livro l2 = new Livro("Git Passo-a-passo");

        Livro[] livros = {l1,l2};

        return Arrays.asList(livros.clone());
    }
}
