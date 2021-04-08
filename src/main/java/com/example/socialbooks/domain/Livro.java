package com.example.socialbooks.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Livro {

    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date publicado;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String editora;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String resumo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Comentario> comentarios;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private  String  autor;
}
