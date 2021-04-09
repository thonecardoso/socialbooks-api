package com.example.socialbooks.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    private String usuario;

    private Date data;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    @JsonIgnore
    private Livro livro;

}
