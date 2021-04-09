package com.example.socialbooks.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalhesErro {

    private String titulo;
    private Long status;
    private Long timestamp;
    private String mensagemDesenvolvedor;

}
