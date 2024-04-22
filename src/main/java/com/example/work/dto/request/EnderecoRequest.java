package com.example.work.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EnderecoRequest {
        private String cep;
        private String rua;
        private String numero;
        private String complemento;
        private String telefone;
}