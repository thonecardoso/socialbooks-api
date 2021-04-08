package com.example.socialbooks.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Comentario {

    private Long id;

    private String texto;

    private String usuario;

    private Date data;
}
