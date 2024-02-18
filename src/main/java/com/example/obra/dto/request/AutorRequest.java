package com.example.obra.dto.request;

import com.example.obra.model.ObraModel;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Data
public class AutorRequest {

    private String cpf;
    private String nome;
    private String paisOrigem;
    private String sexo;
    private String email;
    private Date dataNascimento;

}

