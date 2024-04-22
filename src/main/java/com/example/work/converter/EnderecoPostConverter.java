package com.example.work.converter;

import com.example.work.dto.request.EnderecoRequest;
import com.example.work.model.EnderecoModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EnderecoPostConverter implements Converter<EnderecoRequest, EnderecoModel> {

    @Override
    public EnderecoModel convert(EnderecoRequest source) {
        return EnderecoModel.builder()
                .cep(source.getCep())
                .rua(source.getRua())
                .complemento(source.getComplemento())
                .numero(source.getNumero())
                .telefone(source.getTelefone())
                .dt_criacao(LocalDateTime.now())
                .build();
    }
}