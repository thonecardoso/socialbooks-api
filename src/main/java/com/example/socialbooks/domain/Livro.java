package com.example.socialbooks.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@ToString
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NonNull
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date publicado;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String editora;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String resumo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @OneToMany(mappedBy = "livro", orphanRemoval = true)
    private List<Comentario> comentarios;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private  Autor  autor;
}