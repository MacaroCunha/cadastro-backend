package com.example.obra.dto;

import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Data
@Builder
public class AutorDto {

    private Long id;
    private String cpf;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    private String email;
    private String nome;
    private String paisOrigem;
    private String sexo;

    // Construtores, getters e setters (gerados automaticamente pelo Lombok)

    // Outros métodos, se necessário
}
